package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.interfaceService.IusuarioService;
import com.example.demo.model.Usuario;
import com.example.demo.model.Utility;
import com.example.demo.service.UServiceForgot;

import net.bytebuddy.utility.RandomString;



@Controller
public class LoginController {
	
	@Autowired
	private IusuarioService service;
	
	@Autowired
    private JavaMailSender mailSender;
     
    @Autowired
    private UServiceForgot uServiceForgot;
	
	private final Logger logger= LoggerFactory.getLogger(LoginController.class);
	
	@GetMapping("/")
	public String login(){
		return "login/login.html";
	}
	
	@GetMapping("/acceder")
	public String acceder(Usuario usuario, HttpSession session, Model model) {
		logger.info("Accesos : {}", usuario);
		Optional<Usuario>user = service.listarId(Integer.parseInt(session.getAttribute("idusuario").toString()));
		//logger.info("usuario obtenido: {}",user.get());
		
		
		
		if(user.isPresent()) {
			session.setAttribute("idusuario", user.get().getId());
			if(user.get().getTipo().equals("ADMIN")) {
				return "redirect:/admin/listar";
			}else {
				return "redirect:/";
			}
		}else {
			logger.info("Usuario no existe");
		}
		return "redirect:/admin/listar";
		
	}
	
	@GetMapping("/cerrar")
	public String cerrar(HttpSession session) {
		session.invalidate(); 
		return "redirect:/";
		
	}
	
	 @GetMapping("/forgot_password")
	    public String showForgotPasswordForm() {
		 return "login/forgot_password_form";
	 
	    }
	 
	    @PostMapping("/forgot_password")
	    public String processForgotPassword(HttpServletRequest request, Model model) {
	    	String correo = request.getParameter("correo");
	        String token = RandomString.make(30);
	         
	        try {
	        	uServiceForgot.updateResetPasswordToken(token, correo);
	            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
	            sendEmail(correo, resetPasswordLink);
	            model.addAttribute("message", "Hemos enviado un enlace para la recuperación de contraseña a tu correo, por favor verifica.");
	             
	        } catch (NoSuchElementException  ex) {
	            model.addAttribute("error", ex.getMessage());
	        } catch (UnsupportedEncodingException | MessagingException e) {
	            model.addAttribute("error", "Ocurrió un error mientras de enviaba el correo");
	        }
			return "login/forgot_password_form";
	    }
	     
	    public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException{
	    	MimeMessage message = mailSender.createMimeMessage();              
	        MimeMessageHelper helper = new MimeMessageHelper(message);
	         
	        helper.setFrom("contact@shopme.com", "Soporte Sena");
	        helper.setTo(recipientEmail);
	         
	        String subject = "Aquí está el el enlace para recuperar tu contraseña";
	         
	        String content = "<p>Hola,</p>"
	                + "<p>Ha solicitado restablecer su contraseña.</p>"
	                + "<p>Haga clic en el enlace de abajo para cambiar su contraseña:</p>"
	                + "<p><a href=\"" + link + "\">cambiar mi contraseña</a></p>"
	                + "<br>"
	                + "<p>Ignora este correo electrónico si recuerdas tu contraseña, "
	                + "o no has hecho la solicitud.</p>";
	         
	        helper.setSubject(subject);
	         
	        helper.setText(content, true);
	         
	        mailSender.send(message);
	    }  
	     
	     
	    @GetMapping("/reset_password")
	    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
	    	Usuario usuario = uServiceForgot.getByResetPasswordToken(token);
	        model.addAttribute("token", token);
	         
	        if (usuario == null) {
	            model.addAttribute("message", "Invalid Token");
	            return "message";
	        }
	        return "login/reset_password_form";
	    }
	     
	    @PostMapping("/reset_password")
	    public String processResetPassword(HttpServletRequest request, Model model) {
	    	String token = request.getParameter("token");
	        String pass = request.getParameter("pass");
	         
	        Usuario usuario = uServiceForgot.getByResetPasswordToken(token);
	        model.addAttribute("title", "Reset your password");
	         
	        if (usuario == null) {
	            model.addAttribute("message", "Invalid Token");
	            return "message";
	        } else {           
	        	uServiceForgot.updatePassword(usuario, pass);
	             
	            model.addAttribute("message", "You have successfully changed your password.");
	        }
	         
	        return "login/login_success";
	 
	    }
	
}
