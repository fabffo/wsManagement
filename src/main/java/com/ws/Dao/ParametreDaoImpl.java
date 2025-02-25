/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO IMPLEMENTATION SOCIETE                              ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////

package com.ws.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.ws.beans.Parametre;
import com.ws.beans.ParametreSysteme;
import com.ws.beans.Societe;
import com.ws.menu.MenuItem;
import com.ws.beans.Parametre_detail;
import com.ws.beans.Parametre_ecranGestion_entete;
import com.ws.beans.Parametre_entete;

public class ParametreDaoImpl implements ParametreDao {
	private DaoFactory daoFactory;
	// date du jour
	String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
	private int noOfRecords;
	Map<String, Integer> contactDictionary = new HashMap<>();

	ParametreDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<Parametre_detail> listerDetailParametre(int id_entete) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		 List<Parametre_detail> parametreDetails = new ArrayList<>();
	        String query = "SELECT * FROM tableparametre_detail WHERE id_entete = ? order by ligne";

	        try {
				connexion = daoFactory.getConnection();
				preparedStatement = connexion.prepareStatement(query);

				    preparedStatement.setInt(1, id_entete);
				     resultSet = preparedStatement.executeQuery();

				    while (resultSet.next()) {
				        Parametre_detail parametreDetail = new Parametre_detail();
				        parametreDetail.setId_entete(resultSet.getInt("id_entete"));
				        parametreDetail.setLigne(resultSet.getInt("ligne"));
				        parametreDetail.setType(resultSet.getString("type"));
				        parametreDetail.setNom(resultSet.getString("nom"));
				        parametreDetail.setZone(resultSet.getString("zone"));
				        parametreDetail.setColonne(resultSet.getString("colonne"));
				        parametreDetail.setEcranGestion_largeur(resultSet.getString("ecranGestion_largeur"));
				        parametreDetail.setEcranGestion_recherche(resultSet.getString("ecranGestion_recherche"));
				        parametreDetails.add(parametreDetail);
				    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        return parametreDetails;
	    }

	@Override
	public ParametreSysteme lireEnteteParametre(String parametre) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ParametreSysteme parametreSysteme_entete =null;
	        String query = "SELECT * FROM tableparametre_entete WHERE nom = ?";

	        try {
				connexion = daoFactory.getConnection();
				preparedStatement = connexion.prepareStatement(query);

				    preparedStatement.setString(1, parametre);
				    resultSet = preparedStatement.executeQuery();

				    while (resultSet.next()) {
				        parametreSysteme_entete = new ParametreSysteme();
				        parametreSysteme_entete.setId(resultSet.getInt("id"));
				        parametreSysteme_entete.setNom(resultSet.getString("nom"));
				        parametreSysteme_entete.setLibelle(resultSet.getString("libelle"));
				        parametreSysteme_entete.setNom_ecran_gestion(resultSet.getString("nom_ecran_gestion"));
				        parametreSysteme_entete.setNom_ecran_crud(resultSet.getString("nom_ecran_crud"));
				        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        return parametreSysteme_entete;
	    }

	@Override
	public List<Map<String, Object>> getParametresChampsEcran(String nomProgramme, String entite) throws SQLException {
	    List<Map<String, Object>> listeChamps = new ArrayList<>();
	    String sql = "SELECT * FROM CRUDPARAMETRE_CHAMPS_ECRAN WHERE nom_programme = ? AND entite = ? and nom_champ!='id' order by numero";

	    try (Connection connection = daoFactory.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, nomProgramme);
	        statement.setString(2, entite);

	        try (ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                Map<String, Object> champ = new HashMap<>();
	                champ.put("nom_champ", resultSet.getString("nom_champ"));
	                champ.put("required", resultSet.getBoolean("required"));
	                champ.put("readonly", resultSet.getBoolean("readonly"));
	                champ.put("disabled", resultSet.getBoolean("disabled"));
	                champ.put("minlength", resultSet.getInt("minlength"));
	                champ.put("maxlength", resultSet.getInt("maxlength"));
	                champ.put("type", resultSet.getString("type"));
	                champ.put("step", resultSet.getString("step"));
	                champ.put("placeholder", resultSet.getString("placeholder"));
	                champ.put("type_zone", resultSet.getString("type_zone"));

	                listeChamps.add(champ);
	            }
	        }
	    }
	    return listeChamps;
	}


	@Override
	public Map<String, Map<String, Object>> getMapParametresChampsEcran(String nomProgramme, String entite) throws SQLException {
		Map<String, Map<String, Object>> validations = new HashMap<>();
		Map<String, Object> config = new HashMap<>();

		List<Map<String, Object>> listeChamps = new ArrayList<>();
	    String sql = "SELECT * FROM CRUDPARAMETRE_CHAMPS_ECRAN WHERE nom_programme = ? AND entite = ? order by numero";

	    try (Connection connection = daoFactory.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, nomProgramme);
	        statement.setString(2, entite);
	        try (ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                Map<String, Object> champ = new HashMap<>();
	                champ.put("nom_champ", resultSet.getString("nom_champ"));
	                champ.put("required", resultSet.getBoolean("required"));
	                champ.put("readonly", resultSet.getBoolean("readonly"));
	                champ.put("disabled", resultSet.getBoolean("disabled"));
	                champ.put("minlength", resultSet.getInt("minlength"));
	                champ.put("maxlength", resultSet.getInt("maxlength"));
	                champ.put("type", resultSet.getString("type"));
	                champ.put("step", resultSet.getString("step"));
	                champ.put("placeholder", resultSet.getString("placeholder"));
	                champ.put("type_zone", resultSet.getString("type_zone"));
	                champ.put("valeur", null);
	                validations.put(resultSet.getString("nom_champ"), champ);;
	            }
	        }
	    }
	    return validations;
	}

	@Override
	public List<MenuItem> getMenuItemsFromDatabase() {
        List<MenuItem> allMenuItems = new ArrayList<>();
        Map<Integer, MenuItem> menuMap = new HashMap<>();

        String sql = "SELECT * FROM MENU ORDER BY parent_id, ordre";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String label = resultSet.getString("nom");
                String url = resultSet.getString("url");
                int parentId = resultSet.getInt("parent_id");

                MenuItem menuItem = new MenuItem(id, label, url);
                menuMap.put(id, menuItem);

                if (parentId == 0) {
                    allMenuItems.add(menuItem); // Ajout des menus principaux
                } else {
                	System.out.println(id+label+url+parentId);
                    menuMap.get(parentId).addChild(menuItem); // Ajout des sous-menus
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allMenuItems;
    }


	@Override
	public int genererEnteteParametre(Connection connexion, String entiteName) throws DaoException, SQLException {
		//Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int id_entete = 0;

	        String query = "INSERT INTO Tableparametre_entete (nom, ecranGestion_pasPage, ecranGestion_nbrEnrgPage, pgmcreation, datecreation, usercreation)  VALUES (?, ?, ?, ?, ?, ?);";


				try {
					//connexion = daoFactory.getConnection();
					preparedStatement = connexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
					preparedStatement.setString(1, entiteName);
					 preparedStatement.setInt(2, 2); // ecranGestion_pasPage
					 preparedStatement.setInt(3, 5); // ecranGestion_nbrEnrgPage
			            preparedStatement.setString(4, "GénérerParametre");
			            preparedStatement.setString(5, dateTime);
			            preparedStatement.setString( 6, System.getProperty("user.name"));
			            preparedStatement.executeUpdate();
			            var rs = preparedStatement.getGeneratedKeys();
			            if (rs.next()) {
			                id_entete = rs.getInt(1); // Retourner l'id généré
			            }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return id_entete;
	}


	@Override
	public void genererDetailParametre(Connection connexion, String entiteName, int id_entete, List<Map<String, String>> fieldsInfo) throws DaoException, SQLException {
		//Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String fieldType;
        String fieldName;
		String sql = "INSERT INTO Tableparametre_detail (cle_systeme, id_entete, ligne, type, nom, zone, colonne, ecrangestion_largeur, ecrangestion_recherche, pgmcreation, datecreation, usercreation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			//connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(sql);
			    AtomicInteger lineNumber = new AtomicInteger(1);

			    for (Map<String, String> fieldInfo : fieldsInfo) {
			         fieldType = fieldInfo.get("fieldType");
			         fieldName = fieldInfo.get("fieldName");

			      //Exclure les propriétés log
					if (shouldSkip(fieldName)) {
						 continue; // Ignorer les valeurs
				    }

			        preparedStatement.setString(1, entiteName);
			        preparedStatement.setInt(2, id_entete);
			        preparedStatement.setInt(3, lineNumber.getAndIncrement());
			        preparedStatement.setString(4, fieldType);
			        preparedStatement.setString(5, fieldName);
			        preparedStatement.setString(6, fieldName);
			        preparedStatement.setString(7, fieldName);
			        preparedStatement.setInt(8, 2); // largeur par défaut
			        preparedStatement.setString(9, "O"); // toujours recherche activée
			        preparedStatement.setString(10, "GénérerParametre");
			        preparedStatement.setString(11, dateTime);
			        preparedStatement.setString(12, System.getProperty("user.name"));
			        preparedStatement.addBatch();
			    }
			    preparedStatement.executeBatch();
			   } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }


	@Override
	public void genererCrudparametreChampsEcran(Connection connexion, String entiteName, int id_entete, List<Map<String, String>> fieldsInfo)
			throws DaoException, SQLException {
		//Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String fieldType;
        String fieldName;
		String sql = "INSERT INTO CRUDPARAMETRE_CHAMPS_ECRAN (nom_programme, entite, numero, nom_champ, required, readonly, disabled, minlength, maxlength, type, step, placeholder, pgmcreation, datecreation, usercreation, type_zone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		String[] programmes = { "ajout", "maj", "copie", "visualisation", "renommage", "suppression" };

		try {
			//connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(sql);
			AtomicInteger lineNumber = new AtomicInteger(1);
			for (String programme : programmes) {
				int fieldIndex = 1;

				for (Map<String, String> fieldInfo : fieldsInfo) {
					 fieldType = fieldInfo.get("fieldType");
					 fieldName = fieldInfo.get("fieldName");

					//Exclure les propriétés log
						if (shouldSkip(fieldName)) {
							 continue; // Ignorer les valeurs
					    }
						if (fieldName.equalsIgnoreCase("id")) { // Si l'élément est "SKIP", ignorer cette itération
					        continue;
						}

					preparedStatement.setString(1, programme);
					preparedStatement.setString(2, entiteName);
					preparedStatement.setInt(3, fieldIndex++);
					preparedStatement.setString(4, fieldName);
					preparedStatement.setString(5, "step".equals(fieldName) ||
							"maj".equals(programme) || "copie".equals(programme) || "ajout".equals(programme) || ("renommage".equals(programme)& "nom".equals(fieldName)) ? "true": "false");
					preparedStatement.setString(6,
					"suppression".equals(programme) || "visualisation".equals(programme) || ("renommage".equals(programme ) & !"nom".equals(fieldName)) ? "true" : "false");
					preparedStatement.setString(7, "false");
					preparedStatement.setInt(8, "step".equals(fieldName) ? 0 : 1);

					preparedStatement.setInt(8, 1);
					preparedStatement.setInt(9, 100);
					preparedStatement.setString(10, fieldType);
					preparedStatement.setString(11, "Double".equalsIgnoreCase(fieldType) ? "0.01" : null);
					preparedStatement.setString(12, "Entrez un " + entiteName);
					preparedStatement.setString(13, "GénérerParametre");
					preparedStatement.setString(14, dateTime);
					preparedStatement.setString(15, System.getProperty("user.name"));
					preparedStatement.setString(16, "input");
					preparedStatement.addBatch();
				}
			}
			preparedStatement.executeBatch();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void genererParametres(String entiteName, List<Map<String, String>> fieldsInfo) {
        Connection connection = null;
        int idEntete = 0;

        try {
            // Étape 1 : Obtenir une connexion
            connection = daoFactory.getConnection();

            // Étape 2 : Désactiver l'auto-commit
            connection.setAutoCommit(false);

            // Étape 3 : Générer les enregistrements dans `Tableparametre_entete`
            idEntete = genererEnteteParametre(connection, entiteName);

            // Étape 4 : Générer les enregistrements dans `Tableparametre_detail`
            genererDetailParametre(connection, entiteName, idEntete, fieldsInfo);

            // Étape 5 : Générer les enregistrements dans `CRUDPARAMETRE_CHAMPS_ECRAN`
            genererCrudparametreChampsEcran(connection, entiteName, idEntete, fieldsInfo);

            // Étape 6 : Si tout s'est bien passé, valider la transaction
            connection.commit();

        } catch (Exception e) {
            // Étape 7 : Annuler la transaction en cas d'erreur
            if (connection != null) {
                try {
                    connection.rollback();
                    System.err.println("Transaction annulée en raison d'une erreur : " + e.getMessage());
                } catch (SQLException rollbackEx) {
                    System.err.println("Erreur lors du rollback : " + rollbackEx.getMessage());
                }
            }
        } finally {
            // Étape 8 : Fermer la connexion
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException closeEx) {
                    System.err.println("Erreur lors de la fermeture de la connexion : " + closeEx.getMessage());
                }
            }
        }
	}

	@Override
	public void genererUnParametre(String entiteName, List<Map<String, String>> fieldsInfo) {
        Connection connection = null;
        int id_systeme = 0;

        try {
            // Étape 1 : Obtenir une connexion
            connection = daoFactory.getConnection();

            // Étape 2 : Désactiver l'auto-commit
            connection.setAutoCommit(false);

            // Étape 3 : Générer les enregistrements dans `Tableparametre_entete`
            id_systeme = genererParametreSysteme(connection, entiteName);

            // Étape 4 : Générer les enregistrements dans les tables gestion
            genererParametre_ecranGestion_entete(connection, entiteName, id_systeme, fieldsInfo);
            genererParametre_ecranGestion_ligne(connection, entiteName, id_systeme, fieldsInfo);


            // Étape 5 : Générer les enregistrements dans les tables crud
            genererParametre_ecranCrud_entete(connection, entiteName, id_systeme, fieldsInfo);
            genererParametre_ecranCrud_ligne(connection, entiteName, id_systeme, fieldsInfo);

            // Étape 6 : Si tout s'est bien passé, valider la transaction
            connection.commit();
        } catch (Exception e) {
            // Étape 7 : Annuler la transaction en cas d'erreur
            if (connection != null) {
                try {
                    connection.rollback();
                    System.err.println("Transaction annulée en raison d'une erreur : " + e.getMessage());
                } catch (SQLException rollbackEx) {
                    System.err.println("Erreur lors du rollback : " + rollbackEx.getMessage());
                }
            }
        } finally {
            // Étape 8 : Fermer la connexion
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException closeEx) {
                    System.err.println("Erreur lors de la fermeture de la connexion : " + closeEx.getMessage());
                }
            }
        }
	}

	@Override
	public int genererParametreSysteme(Connection connexion, String entiteName) throws DaoException, SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int id_systeme = 0;

	        String query = "INSERT INTO parametresysteme (nom, libelle, nom_ecran_gestion, nom_ecran_crud, pgmcreation, datecreation, usercreation)  VALUES (?, ?, ?, ?, ?, ?, ?);";


				try {
					//connexion = daoFactory.getConnection();
					preparedStatement = connexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
					preparedStatement.setString(1, entiteName);
					preparedStatement.setString(2, entiteName);
					 preparedStatement.setString(3, "gestionSimple"); // ecranGestion_pasPage
					 preparedStatement.setString(4, "crudSimple"); // ecranGestion_nbrEnrgPage
			            preparedStatement.setString(5, "GénérerParametre");
			            preparedStatement.setString(6, dateTime);
			            preparedStatement.setString( 7, System.getProperty("user.name"));
			            preparedStatement.executeUpdate();
			            var rs = preparedStatement.getGeneratedKeys();
			            if (rs.next()) {
			                id_systeme = rs.getInt(1); // Retourner l'id généré
			            }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return id_systeme ;
	}

	@Override
	public void genererParametre_ecranGestion_entete(Connection connexion, String entiteName, int id_systeme, List<Map<String, String>> fieldsInfo) throws DaoException, SQLException {
		//Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

	        String query = "INSERT INTO parametre_ecrangestion_entete (nom, parametreSysteme, pasPage, nbrEnrgPage, type_entete, pgmcreation, datecreation, usercreation)  VALUES (?, ?, ?, ?, ?, ?, ?, ?);";


				try {
					//connexion = daoFactory.getConnection();
					preparedStatement = connexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
					preparedStatement.setString(1, entiteName);
					preparedStatement.setInt(2, id_systeme);
					 preparedStatement.setInt(3, 2); // ecranGestion_pasPage
					 preparedStatement.setInt(4, 5); // ecranGestion_nbrEnrgPage
					 preparedStatement.setString(5, "standard");
			            preparedStatement.setString(6, "GénérerParametre");
			            preparedStatement.setString(7, dateTime);
			            preparedStatement.setString(8, System.getProperty("user.name"));
			            preparedStatement.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

	@Override
	public void genererParametre_ecranGestion_ligne(Connection connexion, String entiteName, int id_systeme, List<Map<String, String>> fieldsInfo) throws DaoException, SQLException {
		//Connection connexion = null;
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				String fieldType;
		        String fieldName;
				String sql = "INSERT INTO parametre_ecrangestion_ligne (parametreSysteme, numero_ligne, numero_champ, nom_zone, largeur_colonne, type_colonne, zone_recherche, pgmcreation, datecreation, usercreation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

				try {
					preparedStatement = connexion.prepareStatement(sql);
					AtomicInteger lineNumber = new AtomicInteger(1);
					int fieldIndex = 1;

						for (Map<String, String> fieldInfo : fieldsInfo) {
							 fieldType = fieldInfo.get("fieldType");
							 fieldName = fieldInfo.get("fieldName");

							 // Exclure les propriétés log
								if (shouldSkip(fieldName)) {
									continue; // Ignorer les valeurs
								}

							preparedStatement.setInt(1, id_systeme);
							preparedStatement.setInt(2, fieldIndex);
							preparedStatement.setInt(3, fieldIndex);
							preparedStatement.setString(4, fieldName);
							preparedStatement.setInt(5, 2);
							preparedStatement.setString(6, "standard");
							preparedStatement.setString(7, "true");
							preparedStatement.setString(8, "GénérerParametre");
							preparedStatement.setString(9, dateTime);
							preparedStatement.setString(10, System.getProperty("user.name"));
							preparedStatement.addBatch();
							fieldIndex = fieldIndex + 1;
						}
					preparedStatement.executeBatch();
					} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}


	@Override
	public void genererParametre_ecranCrud_entete(Connection connexion, String entiteName, int id_systeme, List<Map<String, String>> fieldsInfo) throws DaoException, SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String fieldType;
        String fieldName;
		String sql = "INSERT INTO parametre_ecrancrud_entete (parametreSysteme, nom_programme, largeur_ecran, pgmcreation, datecreation, usercreation)  VALUES (?, ?, ?, ?, ?, ?);";
		String[] programmes = { "ajout", "maj", "copie", "visualisation", "renommage", "suppression" };

		try {
			// connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(sql);
			AtomicInteger lineNumber = new AtomicInteger(1);
			for (String programme : programmes) {

					preparedStatement.setInt(1, id_systeme);
					preparedStatement.setString(2, programme);
					preparedStatement.setInt(3, 6);
					preparedStatement.setString(4, "GénérerParametre");
					preparedStatement.setString(5, dateTime);
					preparedStatement.setString(6, System.getProperty("user.name"));
					preparedStatement.addBatch();

			}
			preparedStatement.executeBatch();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void genererParametre_ecranCrud_ligne(Connection connexion, String entiteName, int id_systeme, List<Map<String, String>> fieldsInfo)
			throws DaoException, SQLException {
		//Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String fieldType;
        String fieldName;
		String sql = "INSERT INTO parametre_ecrancrud_ligne (parametreSysteme, nom_programme, numero_ligne, numero_champ, nom_champ, required, readonly, disabled,  minlength, maxlength, type, step, placeholder, type_zone, largeur_libelle, pgmcreation, datecreation, usercreation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		String[] programmes = { "ajout", "maj", "copie", "visualisation", "renommage", "suppression" };

		try {
			//connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(sql);
			AtomicInteger lineNumber = new AtomicInteger(1);
			for (String programme : programmes) {
				int fieldIndex = 1;

				for (Map<String, String> fieldInfo : fieldsInfo) {
					 fieldType = fieldInfo.get("fieldType");
					 fieldName = fieldInfo.get("fieldName");

					//Exclure les propriétés log
						if (shouldSkip(fieldName)) {
							 continue; // Ignorer les valeurs
					    }
						if (fieldName.equalsIgnoreCase("id")) { // Si l'élément est "SKIP", ignorer cette itération
					        continue;
						}

					preparedStatement.setInt(1, id_systeme);
					preparedStatement.setString(2, programme);
					preparedStatement.setInt(3, fieldIndex);
					preparedStatement.setInt(4, fieldIndex);
					preparedStatement.setString(5, fieldName);
					preparedStatement.setString(6, "step".equals(fieldName) ||
							"maj".equals(programme) || "copie".equals(programme) || "ajout".equals(programme) || ("renommage".equals(programme)& "nom".equals(fieldName)) ? "true": "false");
					preparedStatement.setString(7,
					"suppression".equals(programme) || "visualisation".equals(programme) || ("renommage".equals(programme ) & !"nom".equals(fieldName)) ? "true" : "false");
					preparedStatement.setString(8, "false");
					preparedStatement.setInt(9, "step".equals(fieldName) ? 0 : 1);
					preparedStatement.setInt(10, 100);
					preparedStatement.setString(11, fieldType);
					preparedStatement.setString(12, "Double".equalsIgnoreCase(fieldType) ? "0.01" : null);
					preparedStatement.setString(13, "Entrez un " + entiteName);
					preparedStatement.setString(14, "input");
					preparedStatement.setInt(15, 12);
					preparedStatement.setString(16, "GénérerParametre");
					preparedStatement.setString(17, dateTime);
					preparedStatement.setString(18, System.getProperty("user.name"));
					preparedStatement.addBatch();
					fieldIndex = fieldIndex + 1;
				}
			}
			preparedStatement.executeBatch();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean shouldSkip(String value) {
	    List<String> exclusions = Arrays.asList("usercreation", "datecreation","pgmcreation","usermodification","datemodification", "pgmmodification" );
	    return exclusions.contains(value.toLowerCase());
	}

}
