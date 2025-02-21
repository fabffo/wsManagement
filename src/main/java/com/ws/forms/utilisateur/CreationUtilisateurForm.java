/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM UTILISATEUR  CREATION                              ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.utilisateur;

import java.util.HashMap;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.UtilisateurDao;
import com.ws.beans.Utilisateur;

import jakarta.servlet.http.HttpServletRequest;

public final class CreationUtilisateurForm {
	private UtilisateurDao typeContratDao;
    private static final String CHAMP_NOM          = "nom";
    private static final String CHAMP_LIBELLE    = "libelle";
    private String              resultat;
    private Map<String, String> erreurs                = new HashMap<String, String>();
    String valeurNom = "";
    String valeurLibelle = "";
   
    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public Utilisateur creerUtilisateur( HttpServletRequest request ) {
    	
    	DaoFactory daoFactory = DaoFactory.getInstance();
		this.typeContratDao = daoFactory.getUtilisateurDao();
        
        String nom = getValeurChamp( request, CHAMP_NOM );
        String libelle = getValeurChamp( request, CHAMP_LIBELLE );
        
        Utilisateur typeContrat = new Utilisateur();

        
        try {
            valeurNom = validationNom( nom );
        } catch ( Exception e ) {
            setErreur( CHAMP_NOM, e.getMessage() );
        }
        
        try {
            valeurLibelle = validationLibelle( libelle );
        } catch ( Exception e ) {
            setErreur( CHAMP_LIBELLE, e.getMessage() );
        }
        
        
        typeContrat.setNom(nom);
        typeContrat.setLibelle(libelle);     

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la création de la Utilisateur.";
        } else {
            resultat = "Échec de la création de la Utilisateur.";
        }
        return typeContrat;
    }

    private String validationNom( String nom ) throws Exception {
        String temp=nom;
        if ( temp != null ) {
                if ( temp.length() > 5 ) {
                    throw new Exception( "Le nom ne doit pas dépasser 5 caractères." );
                } else {
                Boolean existe = typeContratDao.trouverNomUtilisateur(nom);
                	if ( existe == true ) {
                        throw new Exception( "Le nom existe déjà." );
                    } 
                }
            } else {
                throw new Exception( "Merci d'entrer un nom." );
			}
        return temp;
        }
    private String validationLibelle( String libelle ) throws Exception {
        String temp=libelle;
        if ( temp != null ) {
            } else {
                throw new Exception( "Merci d'entrer un libellé." );
			}
        return temp;
        }
    private String validationStatut( String statut ) throws Exception {
        String temp=statut;
        if ( temp != null ) {
                if (( !temp.equals("INTERNE") ) && ( !temp.equals("EXTERNE") )) {
                    throw new Exception( "Le statut ne peut être que"
                    		+ " INTERNE ou EXTERNE." );
                } 
                
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
