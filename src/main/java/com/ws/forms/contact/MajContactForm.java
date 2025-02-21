/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM CONTACT MAJ                                        ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.contact;

import java.util.HashMap;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.ContactDao;
import com.ws.beans.Contact;
import com.ws.beans.TypeContratCollaborateur;

import jakarta.servlet.http.HttpServletRequest;

public final class MajContactForm {
	private ContactDao contactDao;
	private static final String CHAMP_ID          = "id";
    private static final String CHAMP_NOM          = "nom";
    private static final String CHAMP_PRENOM    = "prenom";
    private String              resultat;
    private Map<String, String> erreurs                = new HashMap<String, String>();
    String valeurNom = "";
    String valeurPrenom = "";
    int valeurSignataire = 0;
    String valeurMetier = "";
    int id;
    
    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public Contact modifierContact( HttpServletRequest request ) {
    	DaoFactory daoFactory = DaoFactory.getInstance();
		this.contactDao = daoFactory.getContactDao();
        
        String nom = getValeurChamp( request, CHAMP_NOM );
        String prenom = getValeurChamp( request, CHAMP_PRENOM );
        id = Integer.parseInt(request.getParameter("id"));
    	String adresse= request.getParameter("adresse");
    	String code_postal= request.getParameter("code_postal");
    	String ville= request.getParameter("ville");
    	String pays= request.getParameter("pays");
    	String telephone= request.getParameter("telephone");
    	String email= request.getParameter("email");
    	String telephone_secondaire= request.getParameter("telephone2");	
    	String email_secondaire= request.getParameter("email2");
    	String civilite= request.getParameter("civilite");
    	String societe =request.getParameter("societe");
    	String fonction=request.getParameter("fonction");
      
        Contact contact = new Contact();

        
        try {
            valeurNom = validationNom( nom );
        } catch ( Exception e ) {
            setErreur( CHAMP_NOM, e.getMessage() );
        }
        
        try {
            valeurPrenom = validationPrenom( prenom );
        } catch ( Exception e ) {
            setErreur( CHAMP_PRENOM, e.getMessage() );
        }
        
        contact.setId(id);
        contact.setNom(nom);
        contact.setPrenom(prenom);
        contact.setAdresse(adresse);
        contact.setCode_postal(code_postal);
        contact.setVille(ville);
        contact.setPays(pays);
        contact.setTelephone(telephone);
        contact.setEmail(email);
        contact.setTelephone_secondaire(telephone_secondaire);
        contact.setEmail_secondaire(email_secondaire);
        contact.setCivilite(civilite);
        contact.setSociete(societe);
        contact.setFonction(fonction);
        
        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la création de la Contact.";
        } else {
            resultat = "Échec de la création de la Contact.";
        }
        return contact;
    }

    private String validationNom( String nom ) throws Exception {
        String temp=nom;
        if ( temp != null ) {
                
            } else {
                throw new Exception( "Merci d'entrer un nom." );
			}
        return temp;
        }
    private String validationPrenom( String prenom ) throws Exception {
        String temp=prenom;
        if ( temp != null ) {
            } else {
                throw new Exception( "Merci d'entrer un prénom." );
			}
        return temp;
        }
    
    private int validationSignataire_oui_non( String signataire_oui_non ) throws Exception {
        String temp=signataire_oui_non;
        System.out.print("peut signer"+signataire_oui_non);
        int signataire = 0;
        if ( temp != null ) {
        	if (temp.equals("OUI")) {
        		System.out.print("peut signer"+signataire_oui_non);
        		signataire =1;
        	}else {
        		signataire =0;
        		System.out.print("ne peut pas signer"+signataire_oui_non);
			}
            } else {
                throw new Exception( "Merci d'entrer oui ou non." );
			}
        return signataire;
        }
    
    private String validationMetier( String metier ) throws Exception {
        String temp=metier;
        if ( temp != null ) {
                
                
            } 
        return temp;
        }
    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }	

}
