/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM TYPEPRIX  COPIE                                 ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.typePrix;

import java.util.HashMap;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.TypePrixDao;
import com.ws.beans.TypePrix;

import jakarta.servlet.http.HttpServletRequest;

public final class CopieTypePrixForm {
	private TypePrixDao typePrixDao;
	private static final String CHAMP_ID          = "id";
    private static final String CHAMP_NOM          = "nom";
    private static final String CHAMP_LIBELLE    = "libelle";
    private String              resultat;
    private Map<String, String> erreurs                = new HashMap<String, String>();
    String valeurNom = "";
    String valeurLibelle = "";
    int id;
    
    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public TypePrix copierTypePrix( HttpServletRequest request ) {
    	
    	DaoFactory daoFactory = DaoFactory.getInstance();
		this.typePrixDao = daoFactory.getTypePrixDao();
        
        String nom = getValeurChamp( request, CHAMP_NOM );
        String libelle = getValeurChamp( request, CHAMP_LIBELLE );
        id = Integer.parseInt(request.getParameter("id"));

        TypePrix typePrix = new TypePrix();

        
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
        
        
        typePrix.setId(id);
        typePrix.setNom(nom);
        typePrix.setLibelle(libelle);
     

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la copie de la TypePrix.";
        } else {
            resultat = "Échec de la copie de la TypePrix.";
        }
        return typePrix;
    }

    private String validationCode( String nom ) throws Exception {
        String temp=nom;
        if ( temp != null ) {
            if ( temp.length() > 5 ) {
                throw new Exception( "Le nom ne doit pas dépasser 5 caractères." );
            } else {
            Boolean existe = typePrixDao.trouverNomTypePrix(nom);
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
