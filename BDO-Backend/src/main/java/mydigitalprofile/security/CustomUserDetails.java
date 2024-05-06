package mydigitalprofile.security;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import mydigitalprofile.model.Mitarbeiter;

public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
    private final Mitarbeiter mitarbeiter;
    private final boolean isEnabled;



    /**
     * @param mitarbeiter
     * @param isEnabled
     */
    public CustomUserDetails(Mitarbeiter mitarbeiter, boolean isEnabled) {
        super();
        this.mitarbeiter = mitarbeiter;
        this.isEnabled = isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new LinkedList<>();
        authorities.add(new SimpleGrantedAuthority(this.mitarbeiter.getRolle().toString()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return mitarbeiter.getPasswort();
    }

    @Override
    public String getUsername() {
        return mitarbeiter.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}
