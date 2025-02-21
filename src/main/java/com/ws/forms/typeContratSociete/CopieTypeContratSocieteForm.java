/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM TYPE MISSION COPIE                                 ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.typeContratSociete;

import java.util.HashMap;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.TypeContratSocieteDao;
import com.ws.beans.TypeContratSociete;

import jakarta.servlet.http.HttpServletRequest;

public final class CopieTypeContratSocieteForm {
	private TypeContratSocieteDao typeContratSocieteDao;
	private static final String CHAMP_ID          = "id";
    private static final String CHAMP_NOM          = "nom";
    private static final String CHAMP_LIBELLE    = "libelle";
    private static final String CHAMP_STATUT    = "statut";
    private String              resultat;
    private Map<String, String> erreurs                = new HashMap<String, String>();
    String valeurNom = "";
    String valeurLibelle = "";
    String valeurStatut = "";
    int id;
    
    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public TypeContratSociete copierTypeContratSociete( HttpServletRequest request ) {
    	
    	DaoFactory daoFactory = DaoFactory.getInstance();
		this.typeContratSocieteDao = daoFactory.getTypeContratSocieteDao();
        
        String nom = getValeurChamp( request, CHAMP_NOM );
        String libelle = getValeurChamp( request, CHAMP_LIBELLE );
        String statut = getValeurChamp( request, CHAMP_STATUT );
        id = Integer.parseInt(request.getParameter("id"));

        TypeContratSociete typeContratSociete = new TypeContratSociete();

        
        try {
            valeurNom = validationCode( nom );
        } catch ( Exception e ) {
            setErreur( CHAMP_NOM, e.getMessage() );
        }
        
        try {
            valeurLibelle = validationLibelle( libelle );
        } catch ( Exception e ) {
            setErreur( CHAMP_LIBELLE, e.getMessage() );
        }
        
        try {
            valeurStatut = validationStatut( statut );
        } catch ( Exception e ) {
            setErreur( CHAMP_STATUT, e.getMessage() );
        }
        
        typeContratSociete.setId(id);
        typeContratSociete.setNom(nom);
        typeContratSociete.setLibelle(libelle);
        typeContratSociete.setStatut(statut);
     

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la copie de la TypeContratSociete.";
        } else {
            resultat = "Échec de la copie de la TypeContratSociete.";
        }
        return typeContratSociete;
    }

    private String validationCode( String nom ) throws Exception {
        String temp=nom;
        if ( temp != null ) {
            if ( temp.length() > 5 ) {
                throw new Exception( "Le nom ne doit pas dépasser 5 caractères." );
            } else {
            Boolean existe = typeContratSocieteDao.trouverNomTypeContratSociete(nom);
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
            } else {
                throw new Exception( "Merci d'entrer un statut." );
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
