package mydigitalprofile.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mydigitalprofile.model.Mitarbeiter;
import mydigitalprofile.repository.MitarbeiterRepository;

@Service
@Qualifier("customUserDetailsServiceSecurity")
public class CustomUserDetailsService implements UserDetailsService {

    private final MitarbeiterRepository mitarbeiterRepository;

    public CustomUserDetailsService(MitarbeiterRepository mitarbeiterRepository) {
        super();
        this.mitarbeiterRepository = mitarbeiterRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Mitarbeiter mitarbeiter = mitarbeiterRepository.findByUsername(username);
        if (mitarbeiter != null) {
            return new CustomUserDetails(mitarbeiter, true);
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}