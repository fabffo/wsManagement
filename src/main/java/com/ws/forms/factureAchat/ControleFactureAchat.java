/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME FORM TVA COPIE                                          ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.forms.factureAchat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ws.Dao.DaoFactory;
import com.ws.Dao.FactureAchatDao;
import com.ws.beans.FactureAchat;

import jakarta.servlet.http.HttpServletRequest;

public final class ControleFactureAchat {
	private FactureAchatDao factureAchatDao;
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

    public FactureAchat ControlerExistenceFactureAchat( HttpServletRequest request ) {

    	DaoFactory daoFactory = DaoFactory.getInstance();
		this.factureAchatDao = daoFactory.getFactureAchatDao();

		//Récupération des paramètres
        String nom = getValeurChamp( request, CHAMP_NOM );
        String resume = getValeurChamp( request, CHAMP_LIBELLE );

        FactureAchat factureAchat = new FactureAchat();

        //Contrôle existence du nom factureAchat : le nom doit être unique
        try {
            valeurNom = validationNom( nom );
        } catch ( Exception e ) {
            setErreur( CHAMP_NOM, e.getMessage() );
        }


        // instanciation de la classe factureAchat
        factureAchat.setNom_entite(nom);


        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la copie de la FactureAchat.";
        } else {
            resultat = "Échec de la copie de la FactureAchat.";
        }
        return factureAchat;
    }

    //Contrôle existence du nom
    private String validationNom( String nom ) throws Exception {
        String temp=nom;
            // Boolean existe = factureAchatDao.trouverNomFactureAchat(nom);
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
