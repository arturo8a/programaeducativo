package com.progeduc.controller;

import java.io.InputStream;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Message;

public class Mail {
    
    private final String propertiesFile = "correo.properties";    
    
    public Properties obtenerParametros() throws Exception{
        
        Properties props = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream resourceStream = loader.getResourceAsStream(propertiesFile);
        props.load(resourceStream);
        return props;
    }    
    
    public boolean enviarCorreoRegistro(String correoDestino) {
        
        boolean enviado = true;        
        try {                    
                /* read parameteres */
                //Properties parametros = obtenerParametros();
                String servidorSmtp = "10.10.3.141";
                String emisionCorreo = "adminweb@sunass.gob.pe";
                String emisionAutor = "SistemaEducativoSunass";

                /* create session */
                Properties props = System.getProperties();
                props.put("mail.smtp.host", servidorSmtp);
                Session session = Session.getInstance(props, null);

                /* message */
                MimeMessage msg = new MimeMessage(session);
                msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
                msg.addHeader("format", "flowed");
                msg.addHeader("Content-Transfer-Encoding", "8bit");
                msg.setFrom(new InternetAddress(emisionCorreo,emisionAutor));
                msg.setReplyTo(InternetAddress.parse(emisionCorreo, false));
                msg.setSubject("Confirmación de inscripción al Programa Educativo", "UTF-8");

                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDestino, false));
                
                MimeMultipart multipart = new MimeMultipart("related");
                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent("<p>Sistema del Programa Educativo Sunass</p>Estimado (a) docente, bienvenido (a) al Programa Educativo Aprendiendo a Usar Responsablemente el Agua Potable de la Sunass, en breve recibirá un correo que confirma su inscripción al Programa Educativo.<br>Saludos.", "text/html");
                multipart.addBodyPart(messageBodyPart);
                msg.setContent(multipart);
                Transport.send(msg);
            
        } catch (Exception ex) {
            enviado = false;
            System.out.println("enviarCorreo: "+ex);
        }
        
        return enviado;
    }
    
    public boolean enviarCorreoIEobs(String contenido,String correoDestino) {
        
        boolean enviadoods = true;        
        try {                    
                /* read parameteres */
                //Properties parametros = obtenerParametros();
                String servidorSmtp = "10.10.3.141";
                String emisionCorreo = "adminweb@sunass.gob.pe";
                String emisionAutor = "SistemaEducativoSunass";

                /* create session */
                Properties props = System.getProperties();
                props.put("mail.smtp.host", servidorSmtp);
                Session session = Session.getInstance(props, null);

                /* message */
                MimeMessage msg = new MimeMessage(session);
                msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
                msg.addHeader("format", "flowed");
                msg.addHeader("Content-Transfer-Encoding", "8bit");
                msg.setFrom(new InternetAddress(emisionCorreo,emisionAutor));
                msg.setReplyTo(InternetAddress.parse(emisionCorreo, false));
                msg.setSubject("Error al momento de inscribirse al Programa Educativo", "UTF-8");

                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDestino, false));
                
                MimeMultipart multipart = new MimeMultipart("related");
                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(contenido, "text/html");
                multipart.addBodyPart(messageBodyPart);
                msg.setContent(multipart);
                Transport.send(msg);
            
        } catch (Exception ex) {
            enviadoods = false;
            System.out.println("enviarCorreo: "+ex);
        }
        
        return enviadoods;
    }
    
    
    public boolean enviarCorreoTrabajosFinalesConcursoEscolar(String asunto , String contenido,String correoDestino) {
        
        boolean enviadoods = true;        
        try {                    
                /* read parameteres */
                //Properties parametros = obtenerParametros();
                String servidorSmtp = "10.10.3.141";
                String emisionCorreo = "adminweb@sunass.gob.pe";
                String emisionAutor = "SistemaEducativoSunass";

                /* create session */
                Properties props = System.getProperties();
                props.put("mail.smtp.host", servidorSmtp);
                Session session = Session.getInstance(props, null);

                /* message */
                MimeMessage msg = new MimeMessage(session);
                msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
                msg.addHeader("format", "flowed");
                msg.addHeader("Content-Transfer-Encoding", "8bit");
                msg.setFrom(new InternetAddress(emisionCorreo,emisionAutor));
                msg.setReplyTo(InternetAddress.parse(emisionCorreo, false));
                msg.setSubject(asunto, "UTF-8");

                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDestino, false));
                
                MimeMultipart multipart = new MimeMultipart("related");
                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(contenido, "text/html");
                multipart.addBodyPart(messageBodyPart);
                msg.setContent(multipart);
                Transport.send(msg);
            
        } catch (Exception ex) {
            enviadoods = false;
            System.out.println("enviarCorreo: "+ex);
        }
        
        return enviadoods;
    }
    
    
    
    public boolean enviarCorreoIE(String contenido,String correoDestino) {
        
        boolean enviadoods = true;        
        try {                    
                /* read parameteres */
                //Properties parametros = obtenerParametros();
                String servidorSmtp = "10.10.3.141";
                String emisionCorreo = "adminweb@sunass.gob.pe";
                String emisionAutor = "SistemaEducativoSunass";

                /* create session */
                Properties props = System.getProperties();
                props.put("mail.smtp.host", servidorSmtp);
                Session session = Session.getInstance(props, null);

                /* message */
                MimeMessage msg = new MimeMessage(session);
                msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
                msg.addHeader("format", "flowed");
                msg.addHeader("Content-Transfer-Encoding", "8bit");
                msg.setFrom(new InternetAddress(emisionCorreo,emisionAutor));
                msg.setReplyTo(InternetAddress.parse(emisionCorreo, false));
                msg.setSubject("Confirmación de inscripción al Programa Educativo", "UTF-8");

                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDestino, false));
                
                MimeMultipart multipart = new MimeMultipart("related");
                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(contenido, "text/html");
                multipart.addBodyPart(messageBodyPart);
                msg.setContent(multipart);
                Transport.send(msg);
            
        } catch (Exception ex) {
            enviadoods = false;
            System.out.println("enviarCorreo: "+ex);
        }
        
        return enviadoods;
    }
    
    
    public boolean enviarCorreoVerificacion(String contenido,String correoDestino) {
        
        boolean enviadoods = true;        
        try {                    
                /* read parameteres */
                //Properties parametros = obtenerParametros();
                String servidorSmtp = "10.10.3.141";
                String emisionCorreo = "adminweb@sunass.gob.pe";
                String emisionAutor = "SistemaEducativoSunass";

                /* create session */
                Properties props = System.getProperties();
                props.put("mail.smtp.host", servidorSmtp);
                Session session = Session.getInstance(props, null);

                /* message */
                MimeMessage msg = new MimeMessage(session);
                msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
                msg.addHeader("format", "flowed");
                msg.addHeader("Content-Transfer-Encoding", "8bit");
                msg.setFrom(new InternetAddress(emisionCorreo,emisionAutor));
                msg.setReplyTo(InternetAddress.parse(emisionCorreo, false));
                msg.setSubject("Correo de Verificación", "UTF-8");

                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDestino, false));
                
                MimeMultipart multipart = new MimeMultipart("related");
                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(contenido, "text/html");
                multipart.addBodyPart(messageBodyPart);
                msg.setContent(multipart);
                Transport.send(msg);
            
        } catch (Exception ex) {
            enviadoods = false;
            System.out.println("enviarCorreo: "+ex);
        }
        
        return enviadoods;
    }

}
