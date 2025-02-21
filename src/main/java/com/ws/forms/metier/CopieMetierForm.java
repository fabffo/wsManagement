/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM METIER  COPIE                                      ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.metier;

import java.util.HashMap;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.MetierDao;
import com.ws.beans.Metier;

import jakarta.servlet.http.HttpServletRequest;

public final class CopieMetierForm {
	private MetierDao metierDao;
	private static final String CHAMP_ID          = "id";
    private static final String CHAMP_NOM          = "nom";
    private static final String CHAMP_DOMAINE    = "domaine";
    private String              resultat;
    private Map<String, String> erreurs                = new HashMap<String, String>();
    String valeurNom = "";
    String valeurDomaine = "";
    int id;

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public Metier copierMetier( HttpServletRequest request ) {

    	DaoFactory daoFactory = DaoFactory.getInstance();
		this.metierDao = daoFactory.getMetierDao();

        String nom = getValeurChamp( request, CHAMP_NOM );
        String domaine = getValeurChamp( request, CHAMP_DOMAINE );


        Metier metier = new Metier();


        try {
            valeurNom = validationCode( nom );
        } catch ( Exception e ) {
            setErreur( CHAMP_NOM, e.getMessage() );
        }

        try {
            valeurDomaine = validationDomaine( domaine );
        } catch ( Exception e ) {
            setErreur( CHAMP_DOMAINE, e.getMessage() );
        }


        metier.setId(id);
        metier.setNom(nom);
        metier.setDomaine(domaine);


        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la copie de la metier.";
        } else {
            resultat = "Échec de la copie de la metier.";
        }
        return metier;
    }

    private String validationCode( String nom ) throws Exception {
        String temp=nom;
        if ( temp != null ) {
            Boolean existe = metierDao.trouverNomMetier(nom);
            	if ( existe == true ) {
                    throw new Exception( "Le nom existe déjà." );
                }
        } else {
            throw new Exception( "Merci d'entrer un nom." );
		}
        return temp;
        }
    private String validationDomaine( String domaine ) throws Exception {
        String temp=domaine;
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
