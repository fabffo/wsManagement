/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM TVA COPIE                                          ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.tva;

import java.util.HashMap;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.GenericObjectFactory;
import com.ws.Dao.TvaDao;
import com.ws.beans.Tva;

import jakarta.servlet.http.HttpServletRequest;

public final class ControleExistenceTva {
	private TvaDao tvaDao;
	private static final String CHAMP_ID           = "id";
    private static final String CHAMP_NOM          = "nom";
    private static final String CHAMP_LIBELLE      = "libelle";
    private static final String CHAMP_TAUX       = "taux";
    private String              resultat;
    private Map<String, String> erreurs                = new HashMap<String, String>();
    String valeurNom = "";
    String valeurLibelle = "";
    String valeurTaux = "";
    int id;
    float taux = 0;

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public Tva ControlerExistenceTva( HttpServletRequest request ) {

    	DaoFactory daoFactory = DaoFactory.getInstance();
		this.tvaDao = daoFactory.getTvaDao();

		//Récupération des paramètres
        String nom = getValeurChamp( request, CHAMP_NOM );
        String libelle = getValeurChamp( request, CHAMP_LIBELLE );

        Tva tva = new Tva();

        //Contrôle existence du nom tva : le nom doit être unique
        try {
            valeurNom = validationNom( nom );
        } catch ( Exception e ) {
            setErreur( CHAMP_NOM, e.getMessage() );
        }

        // instanciation de la classe tva
        tva.setId(id);
        tva.setNom(nom);
        tva.setLibelle(libelle);
        tva.setTaux((double) taux);

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la copie de la Tva.";
        } else {
            resultat = "Échec de la copie de la Tva.";
        }
        return tva;
    }

    //Contrôle existence du nom
    private String validationNom( String nom ) throws Exception {
        String temp=nom;
            Boolean existe = tvaDao.trouverNomTva(nom);
            	if ( existe == true ) {
                    throw new Exception( "Le nom existe déjà." );
                }
        return temp;
        }


     // Ajoute un message correspondant au champ spécifié à la map des erreurs.
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

     //Méthode utilitaire qui retourne null si un champ est vide, et son contenu
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }

  //Méthode utilitaire qui retourne un float
    private static Float getValeurFloat( HttpServletRequest request, String nomFloat ) {
        Float valeur = Float.parseFloat(request.getParameter( nomFloat ));
            return valeur;
    }

}
