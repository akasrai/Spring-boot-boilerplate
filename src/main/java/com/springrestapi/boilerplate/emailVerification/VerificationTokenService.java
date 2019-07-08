package com.springrestapi.boilerplate.emailVerification;

import com.springrestapi.boilerplate.mail.MailService;
import com.springrestapi.boilerplate.user.User;
import com.springrestapi.boilerplate.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VerificationTokenService {

    private MailService mailService;

    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private UserService userService;

    @Autowired
    public VerificationTokenService(VerificationTokenRepository verificationTokenRepository, MailService mailService){
        this.verificationTokenRepository = verificationTokenRepository;
        this.mailService = mailService;
    }


    public void createVerification(String email){
        User user = userService.findByEmail(email);

        List<VerificationToken> verificationTokens = verificationTokenRepository.findByUserEmail(email);
        VerificationToken verificationToken;
        if (verificationTokens.isEmpty()) {
            verificationToken = new VerificationToken();
            verificationToken.setUser(user);
            verificationTokenRepository.save(verificationToken);
        } else {
            verificationToken = verificationTokens.get(0);
        }

        mailService.sendVerificationMail(email, verificationToken.getToken());
    }

    public ResponseEntity<String> verifyEmail(String token){
        List<VerificationToken> verificationTokens = verificationTokenRepository.findByToken(token);
        if (verificationTokens.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid token.");
        }

        VerificationToken verificationToken = verificationTokens.get(0);
        if (verificationToken.getExpiredDateTime().isBefore(LocalDateTime.now())) {
            return ResponseEntity.unprocessableEntity().body("Expired token.");
        }

        verificationToken.setConfirmedDateTime(LocalDateTime.now());
        verificationToken.setStatus(Status.VERIFIED);
        verificationToken.getUser().setIsEmailVerified(true);
        verificationTokenRepository.save(verificationToken);

        return ResponseEntity.ok("You have successfully verified your email address.");
    }
}