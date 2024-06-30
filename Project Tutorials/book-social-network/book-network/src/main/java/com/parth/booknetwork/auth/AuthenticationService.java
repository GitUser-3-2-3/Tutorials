package com.parth.booknetwork.auth;

import com.parth.booknetwork.email.EmailService;
import com.parth.booknetwork.email.EmailTemplateName;
import com.parth.booknetwork.role.RoleRepository;
import com.parth.booknetwork.user.Token;
import com.parth.booknetwork.user.TokenRepository;
import com.parth.booknetwork.user.User;
import com.parth.booknetwork.user.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

   private final RoleRepository roleRepository;
   private final PasswordEncoder passwordEncoder;
   private final UserRepository userRepository;
   private final TokenRepository tokenRepository;
   private final EmailService emailService;

   @Value("${application.mailing.frontend.activation-url}")
   private String activationUrl;

   public void register(RegistrationRequest request) throws MessagingException {
      var userRole = roleRepository.findByName("USER")
           // todo - better exception handling
           .orElseThrow(() -> new IllegalStateException("ROLE USER was not initialized"));

      var user = User.builder().firstname(request.getFirstname())
           .lastname(request.getLastname())
           .email(request.getEmail())
           .password(passwordEncoder.encode(request.getPassword()))
           .accountLocked(false).enabled(false)
           .roles(List.of(userRole))
           .build();

      userRepository.save(user);
      sendValidationEmail(user);
   }

   private void sendValidationEmail(User user) throws MessagingException {
      var newToken = generateAndSaveNewToken(user);

      emailService.sendEmail(
           user.getEmail(), user.fullName(),
           EmailTemplateName.ACTIVATE_ACCOUNT,
           activationUrl, newToken,
           "Account Activation"
      );
   }

   private String generateAndSaveNewToken(User user) {
      // generate a token
      String generatedToken = generateActivationCode(6);

      var token = Token.builder().token(generatedToken)
           .createdAt(LocalDateTime.now())
           .expiresAt(LocalDateTime.now().plusMinutes(15))
           .user(user)
           .build();

      tokenRepository.save(token);
      return generatedToken;
   }

   private String generateActivationCode(int length) {
      String characters = "0123456789";
      StringBuilder codeBuilder = new StringBuilder();
      SecureRandom secureRandom = new SecureRandom();

      for (int i = 0; i < length; i++) {
         int randomIndex = secureRandom.nextInt(characters.length());
         codeBuilder.append(characters.charAt(randomIndex));
      }
      return codeBuilder.toString();
   }
}
