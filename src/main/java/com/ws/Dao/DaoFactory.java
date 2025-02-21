/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO FACTORY                                             ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {

	private static DaoFactory instance;

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/ws_ods_dev" ;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "R@@tMysql0270";


	 // Méthode pour obtenir une instance de DaoFactory
	    public static DaoFactory getInstance() {
	        if (instance == null) {
	            synchronized (DaoFactory.class) {
	                if (instance == null) {
	                    try {
	                        Class.forName("com.mysql.jdbc.Driver");
	                    } catch (ClassNotFoundException e) {
	                        throw new RuntimeException("Driver MySQL non trouvé.", e);
	                    }
	                    instance = new DaoFactory();
	                }
	            }
	        }
	        return instance;
	    }
	    // Méthode pour obtenir une connexion à partir du pool
	    public Connection getConnection() throws SQLException {
	    	//Connection connection = DatasourceH.getConnection();
	    	Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	        connection.setAutoCommit(false); // Set autocommit to false
	        return connection;
	    }

	    // Récupération du Dao
	    public TypeContratCollaborateurDao getTypeContratCollaborateurDao() {
	        return new TypeContratCollaborateurDaoImpl(this);
	    }

	 // Récupération du Dao
	    public TypeContratSocieteDao getTypeContratSocieteDao() {
	        return new TypeContratSocieteDaoImpl(this);
	    }

	 // Récupération du Dao
	    public CalendrierDao getCalendrierDao() {
	        return new CalendrierDaoImpl(this);
	    }

	 // Récupération du Dao
	    public TypeSocieteDao getTypeSocieteDao() {
	        return new TypeSocieteDaoImpl(this);
	    }

	 // Récupération du Dao
	    public CollaborateurDao getCollaborateurDao() {
	        return new CollaborateurDaoImpl(this);
	    }

	 // Récupération du Dao
	    public SocieteDao getSocieteDao() {
	        return new SocieteDaoImpl(this);
	    }

	    // Récupération du Dao
	    public TvaDao getTvaDao() {
	        return new TvaDaoImpl(this);
	    }

	 // Récupération du Dao
	    public MetierDao getMetierDao() {
	        return new MetierDaoImpl(this);
	    }

	 // Récupération du Dao
	    public UtilisateurDao getUtilisateurDao() {
	        return new UtilisateurDaoImpl(this);
	    }

	 // Récupération du Dao
	    public ContactDao getContactDao() {
	        return new ContactDaoImpl(this);
	    }

	 // Récupération du Dao
	    public TypePrixDao getTypePrixDao() {
	        return new TypePrixDaoImpl(this);
	    }

	 // Récupération du Dao
	    public TypeDocumentDao getTypeDocumentDao() {
	        return new TypeDocumentDaoImpl(this);
	    }

	 // Récupération du Dao
	    public ContratDao getContratDao() {
	        return new ContratDaoImpl(this);
	    }

	    // Récupération du Dao
	    public MissionDao getMissionDao() {
	        return new MissionDaoImpl(this);
	    }

	    // Récupération du Dao
	    public EcritureBanque_importDao getEcritureBanque_importDao() {
	        return new EcritureBanque_importDaoImpl(this);
	    }

	 // Récupération du Dao
	    public FactureAchatDao getFactureAchatDao() {
	        return new FactureAchatDaoImpl(this);
	    }

	 // Récupération du Dao
	    public ParametreDao getParametreDao() {
	        return new ParametreDaoImpl(this);
	    }
	 // Récupération du Dao
	    public ParametreEcranDao getParametreEcranDao() {
	        return new ParametreEcranDaoImpl(this);
	    }

	 // Récupération du Dao
	    public ParametreSystemeDao getParametreSystemeDao() {
	        return new ParametreSystemeDaoImpl(this);
	    }

	 // Récupération du Dao
	    public ParametreSysteme_detailDao getParametreSysteme_detailDao() {
	        return new ParametreSysteme_detailDaoImpl(this);
	    }

	 // Récupération du Dao
	    public CrudParametre_champs_ecranDao getCrudParametre_champs_ecranDao() {
	        return new CrudParametre_champs_ecranDaoImpl(this);
	    }

	 // Récupération du Dao
	    public Parametre_ecranCrud_enteteDao getParametre_ecranCrud_enteteDao() {
	        return new Parametre_ecranCrud_enteteDaoImpl(this);
	    }

	 // Récupération du Dao
	    public Parametre_ecranCrud_ligneDao getParametre_ecranCrud_ligneDao() {
	        return new Parametre_ecranCrud_ligneDaoImpl(this);
	    }

	 // Récupération du Dao
	    public Parametre_ecranGestion_enteteDao getParametre_ecranGestion_enteteDao() {
	        return new Parametre_ecranGestion_enteteDaoImpl(this);
	    }

	 // Récupération du Dao
	    public Parametre_ecranGestion_ligneDao getParametre_ecranGestion_ligneDao() {
	        return new Parametre_ecranGestion_ligneDaoImpl(this);
	    }


	 // Récupération du Dao
	    public MenuDao getMenuDao() {
	        return new MenuDaoImpl(this);
	    }

	 // Récupération du Dao
	    public StatutDao getStatutDao() {
	        return new StatutDaoImpl(this);
	    }

	 // Récupération du Dao
	    public TypeContratDao getTypeContratDao() {
	        return new TypeContratDaoImpl(this);
	    }

	 // Récupération du Dao
	    public EntiteDao getEntiteDao() {
	        return new EntiteDaoImpl(this);
	    }

	 // Récupération du Dao
	    public ActiviteDao getActiviteDao() {
	        return new ActiviteDaoImpl(this);
	    }

	 // Récupération du Dao
	    public NationaliteDao getNationaliteDao() {
	        return new NationaliteDaoImpl(this);
	    }

	 // Récupération du Dao
	    public SignataireDao getSignataireDao() {
	        return new SignataireDaoImpl(this);
	    }

	 // Récupération du Dao
	    public PersonnelDao getPersonnelDao() {
	        return new PersonnelDaoImpl(this);
	    }

	 // Récupération du Dao
	    public RelationDao getRelationDao() {
	        return new RelationDaoImpl(this);
	    }

	 // Récupération du Dao
	    public OrganisationDao getOrganisationDao() {
	        return new OrganisationDaoImpl(this);
	    }

	 // Récupération du Dao
	    public TypeIntervenantDao getTypeIntervenantDao() {
	        return new TypeIntervenantDaoImpl(this);
	    }

	 // Récupération du Dao
	    public AccordOrganisationDao getAccordOrganisationDao() {
	        return new AccordOrganisationDaoImpl(this);
	    }

	 // Récupération du Dao
	    public ClientDao getClientDao() {
	        return new ClientDaoImpl(this);
	    }

	 // Récupération du Dao
	    public ActionDao getActionDao() {
	        return new ActionDaoImpl(this);
	    }
}
