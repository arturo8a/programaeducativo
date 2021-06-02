package com.progeduc.componente;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import com.progeduc.model.*;

@Component
public class Ldap {
    
    
    private static final Logger log = Logger.getLogger(Ldap.class);
    private final String propertiesLdapFile = "ldap.properties";
    
    
    public DirContext conectarLDAP(Properties props) throws Exception {
        
        String ldapPrefix = props.getProperty("ldap.prefix");
        String ldapTimeout = props.getProperty("ldap.timeout");
        String ldapUrl = props.getProperty("ldap.url");
        String ldapUser = props.getProperty("ldap.user");
        String ldapPassword = props.getProperty("ldap.password");
        
        DirContext dirContext = null;
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, ldapUrl);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, ldapUser + ldapPrefix);
        env.put(Context.SECURITY_CREDENTIALS, ldapPassword);
        env.put("com.sun.jndi.ldap.connect.timeout", ldapTimeout);
        
        dirContext = new InitialDirContext(env);
        
        return dirContext;
    }

    
    public Properties obtenerParametros() throws Exception{
        
        Properties props = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream resourceStream = loader.getResourceAsStream(propertiesLdapFile);
        props.load(resourceStream);
        return props;
    }
    
    
    public NamingEnumeration<?> obtenerGruposLDAP(DirContext dirContext,Properties props) throws Exception {
        
        String ldapGroup = props.getProperty("ldap.group");
        String ldapCN = props.getProperty("ldap.cnSearch");
        
        NamingEnumeration<?> usersLDAP = null;
        
        SearchControls constraints = new SearchControls();

        constraints.setReturningAttributes(new String[]{"cn", "member"});
        constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration<SearchResult> groupAnswer = dirContext.search(ldapCN, "(&(objectCategory=group)(cn=" + ldapGroup + "))", constraints);

        if (groupAnswer.hasMore()) {
            Attributes attrs = ((SearchResult) groupAnswer.next()).getAttributes();
            log.debug("Group: " + attrs.get("cn").get());
            if (attrs.get("member") != null) {
                usersLDAP = attrs.get("member").getAll();
            }
        } else {
            log.debug("Not exists group");
        }
              
        return usersLDAP;
    }

    public Attributes obtenerUsuarioLDAP(DirContext dirContext, String userCN) throws Exception {
        Attributes attrs = null;
        SearchControls constraints = new SearchControls();
        constraints.setReturningAttributes(new String[] {"sn", "givenName", "SAMAccountName"});
        constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
        String filter = "(&(objectCategory=person)(objectClass=user))";
        NamingEnumeration<SearchResult> userLDAP = dirContext.search(userCN, filter, constraints);
        if (userLDAP.hasMore()) {
            attrs = ((SearchResult) userLDAP.next()).getAttributes();
        }
        return attrs;
    }
    
    public List<UsuarioLdap> buscarUsuariosLDAP() throws Exception{
        
        Properties props = obtenerParametros();

        List<UsuarioLdap> listaUsuario=new ArrayList<>();
        DirContext dirContext = this.conectarLDAP(props);
        NamingEnumeration<?> gruposLDAP = this.obtenerGruposLDAP(dirContext,props);

        if (gruposLDAP != null) {
            while (gruposLDAP.hasMore()) {
                String userCN = gruposLDAP.next().toString();
                Attributes attrs = this.obtenerUsuarioLDAP(dirContext, userCN);
                if (attrs != null && attrs.size() == 3) {
                    UsuarioLdap userLdap=new UsuarioLdap();
                    userLdap.setCuenta( (String) attrs.get("SAMAccountName").get() );
                    userLdap.setNombre( ((String) attrs.get("givenName").get()).toUpperCase().trim() );
                    userLdap.setUsuario( ((String) attrs.get("sn").get()).toUpperCase().trim() );

                    listaUsuario.add(userLdap);
                }
            }
        }
        return listaUsuario;
    }
    
    
    public DirContext validarUsuario(Properties props,String usuario,String clave) throws Exception {
        
        String ldapPrefix = props.getProperty("ldap.prefix");
        String ldapTimeout = props.getProperty("ldap.timeout");
        String ldapUrl = props.getProperty("ldap.url");
        String ldapUser = usuario;
        String ldapPassword = clave;
        
        DirContext dirContext = null;
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, ldapUrl);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, ldapUser + ldapPrefix);
        env.put(Context.SECURITY_CREDENTIALS, ldapPassword);
        env.put("com.sun.jndi.ldap.connect.timeout", ldapTimeout);
        
        dirContext = new InitialDirContext(env);
        
        return dirContext;
    }
    
    
    /* LDAP X GRUPO */
    
    public NamingEnumeration<?> obtenerGruposLDAP(DirContext dirContext, Properties props,String grupo) throws Exception {

        String ldapCN = props.getProperty("ldap.cnSearch");

        NamingEnumeration<?> usersLDAP = null;

        SearchControls constraints = new SearchControls();

        constraints.setReturningAttributes(new String[]{"cn", "member"});
        constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration<SearchResult> groupAnswer = dirContext.search(ldapCN, "(&(objectCategory=group)(cn=" + grupo + "))", constraints);

        if (groupAnswer.hasMore()) {
            Attributes attrs = ((SearchResult) groupAnswer.next()).getAttributes();
            log.debug("Group: " + attrs.get("cn").get());
            if (attrs.get("member") != null) {
                usersLDAP = attrs.get("member").getAll();
            }
        } else {
            log.debug("Not exists group");
        }

        return usersLDAP;
    }
    
    public List<UsuarioLdap> buscarUsuariosLdapPorGrupo(String grupo) throws Exception {

        Properties props = obtenerParametros();

        List<UsuarioLdap> listaUsuario = new ArrayList<>();
        DirContext dirContext = this.conectarLDAP(props);
        NamingEnumeration<?> gruposLDAP = this.obtenerGruposLDAP(dirContext, props, grupo);

        if (gruposLDAP != null) {
            while (gruposLDAP.hasMore()) {
                String userCN = gruposLDAP.next().toString();
                Attributes attrs = this.obtenerUsuarioLDAP(dirContext, userCN);
                if (attrs != null && attrs.size() == 3) {
                    UsuarioLdap userLdap = new UsuarioLdap();
                    userLdap.setCuenta((String) attrs.get("SAMAccountName").get());
                    userLdap.setNombre(((String) attrs.get("givenName").get()).toUpperCase().trim());
                    userLdap.setUsuario(((String) attrs.get("sn").get()).toUpperCase().trim());

                    listaUsuario.add(userLdap);
                }
            }
        }
        return listaUsuario;
    }
    
    
    public List<UsuarioLdap> listarTodosUsuariosLDAP() throws Exception{
        
        List<UsuarioLdap> listaUsuario = new ArrayList<>();
        
        Properties props = obtenerParametros();
        DirContext ldapContext = conectarLDAP(props);
        
        SearchControls searchCtls = new SearchControls();
        String returnedAtts[]={"sn","givenName", "samAccountName"};
        searchCtls.setReturningAttributes(returnedAtts);
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        String searchFilter = "(&(objectClass=user))";
        
        //String searchBase = "OU=Usuarios,DC=sunass,DC=gob,DC=pe";
        String searchBaseLima = "OU=Lima,OU=Usuarios,DC=sunass,DC=gob,DC=pe";
        String searchBaseProvincia = "OU=Provincias,OU=Usuarios,DC=sunass,DC=gob,DC=pe";

        
        NamingEnumeration<SearchResult> answerLima = ldapContext.search(searchBaseLima, searchFilter, searchCtls);
        NamingEnumeration<SearchResult> answerProvincia = ldapContext.search(searchBaseProvincia, searchFilter, searchCtls);

        while (answerLima.hasMoreElements()){
            SearchResult sr = (SearchResult)answerLima.next();
            Attributes attrs = sr.getAttributes();
            UsuarioLdap userLdap = new UsuarioLdap();
            userLdap.setCuenta((String) (attrs.get("SAMAccountName")==null?"":attrs.get("SAMAccountName").get()) );
            userLdap.setNombre(((String) (attrs.get("givenName")==null?"":attrs.get("givenName").get()) ).toUpperCase().trim());
            userLdap.setUsuario(((String) (attrs.get("sn")==null?"":attrs.get("sn").get()) ).toUpperCase().trim());
            listaUsuario.add(userLdap);
        }

        while (answerProvincia.hasMoreElements()){
            SearchResult sr = (SearchResult)answerProvincia.next();
            Attributes attrs = sr.getAttributes();
            UsuarioLdap userLdap = new UsuarioLdap();
            userLdap.setCuenta((String) (attrs.get("SAMAccountName")==null?"":attrs.get("SAMAccountName").get()) );
            userLdap.setNombre(((String) (attrs.get("givenName")==null?"":attrs.get("givenName").get()) ).toUpperCase().trim());
            userLdap.setUsuario(((String) (attrs.get("sn")==null?"":attrs.get("sn").get()) ).toUpperCase().trim());
            listaUsuario.add(userLdap);
        }
        
        ldapContext.close();
        return listaUsuario;
    }
    
}
