package com.baeldung.roles.custom.web;

import com.baeldung.roles.custom.persistence.dao.OrganizationRepository;
import com.baeldung.roles.custom.persistence.model.Foo;
import com.baeldung.roles.custom.persistence.model.Organization;
import com.baeldung.roles.custom.security.MyUserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    private final OrganizationRepository organizationRepository;

    public MainController(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    // @PostAuthorize("hasPermission(returnObject, 'read')")
    @PreAuthorize("hasPermission(#id, 'Foo', 'read')")
    @GetMapping("/foos/{id}")

    public Foo findById(@PathVariable final long id) {
        return new Foo("Sample");
    }

    @PreAuthorize("hasPermission(#foo, 'write')")
    @PostMapping("/foos")
    @ResponseStatus(HttpStatus.CREATED)

    public Foo create(@RequestBody final Foo foo) {
        return foo;
    }

    @PreAuthorize("hasAuthority('FOO_READ_PRIVILEGE')")
    @GetMapping("/foos")

    public Foo findFooByName(@RequestParam final String name) {
        return new Foo(name);
    }

    @PreAuthorize("isMember(#id)")
    @GetMapping("/organizations/{id}")
    public Organization findOrgById(@PathVariable final long id) {
        return organizationRepository.findById(id)
                .orElse(null);
    }

    @PreAuthorize("hasPermission(#id, 'Foo', 'read')")
    @GetMapping("/user")

    public MyUserPrincipal retrieveUserDetails(@AuthenticationPrincipal MyUserPrincipal principal) {
        return principal;
    }
}
