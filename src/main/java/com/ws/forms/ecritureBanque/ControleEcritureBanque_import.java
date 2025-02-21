/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM TVA COPIE                                          ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.ecritureBanque;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.EcritureBanque_importDao;
import com.ws.beans.EcritureBanque_import;

import jakarta.servlet.http.HttpServletRequest;

public final class ControleEcritureBanque_import {
	private EcritureBanque_importDao ecritureBanque_importDao;
	private static final String CHAMP_ID           = "date";
    private static final String CHAMP_NOM          = "nom";
    private static final String CHAMP_LIBELLE      = "resume";
    private String              resultat;
    private Map<String, String> erreurs                = new HashMap<String, String>();
    String valeurNom = "";
    String valeurResume = "";


    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public EcritureBanque_import ControlerExistenceEcritureBanque_import( HttpServletRequest request ) {

    	DaoFactory daoFactory = DaoFactory.getInstance();
		this.ecritureBanque_importDao = daoFactory.getEcritureBanque_importDao();

		//Récupération des paramètres
        String nom = getValeurChamp( request, CHAMP_NOM );
        String resume = getValeurChamp( request, CHAMP_LIBELLE );

        EcritureBanque_import ecritureBanque_import = new EcritureBanque_import();

        //Contrôle existence du nom ecritureBanque_import : le nom doit être unique
        try {
            valeurNom = validationNom( nom );
        } catch ( Exception e ) {
            setErreur( CHAMP_NOM, e.getMessage() );
        }


        // instanciation de la classe ecritureBanque_import
        ecritureBanque_import.setNom_import(nom);


        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la copie de la EcritureBanque_import.";
        } else {
            resultat = "Échec de la copie de la EcritureBanque_import.";
        }
        return ecritureBanque_import;
    }

    //Contrôle existence du nom
    private String validationNom( String nom ) throws Exception {
        String temp=nom;
            // Boolean existe = ecritureBanque_importDao.trouverNomEcritureBanque_import(nom);
        		Boolean existe = false;
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
