package com.in28minuts.rest.webservice.demo.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.in28minuts.rest.webservice.demo.exception.UserNotFoundException;
import com.in28minuts.rest.webservice.demo.post.Post;
import com.in28minuts.rest.webservice.demo.post.PostRepository;
import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public UserJpaResource(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    // Implementing HEATEOAS
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUserById(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("id-" + id);
        }
        EntityModel<User> resource = EntityModel.of(user.get());
        // Adding link to retrieveAllUsers
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUserById(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/post")
    public List<Post> retrivePostsForUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("id-" + id);
        }
        return user.get().getPosts();
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                            .buildAndExpand(user.getId())
                                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/jpa/users/{id}/post")
    public ResponseEntity<Object> createPostsForUser(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("id-" + id);
        }
        post.setUser(user.get());
        Post savedpost = postRepository.save(post);
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                            .buildAndExpand(savedpost.getId())
                                .toUri();
        return ResponseEntity.created(location).build();
    }

    
}
