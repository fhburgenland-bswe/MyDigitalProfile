package mydigitalprofile.service;

import mydigitalprofile.model.TeamMember;
import mydigitalprofile.repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("customUserDetailsServiceService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private TeamMemberRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TeamMember teamMember = repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        return new org.springframework.security.core.userdetails.User(teamMember.getEmail(), "", new ArrayList<>());
    }
}