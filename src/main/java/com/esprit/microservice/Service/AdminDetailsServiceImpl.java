package com.esprit.microservice.Service;


import com.esprit.microservice.Repository.ResponsableSiegeRepository;
import com.esprit.microservice.Entity.ResponsableSiege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminDetailsServiceImpl implements UserDetailsService {
  @Autowired
  ResponsableSiegeRepository ResponsableSiegeRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    ResponsableSiege admin = ResponsableSiegeRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return AdminDetailsImpl.build(admin);
  }

}
