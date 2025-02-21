/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME DAO IMPLEMENTATION TYPE MISSION                         ///
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
import java.util.Date;
import java.util.List;

import com.ws.beans.TypeContratSociete;


public class TypeContratSocieteDaoImpl implements TypeContratSocieteDao {
	private DaoFactory daoFactory;
	//date du jour
    String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());

    private int noOfRecords;

    TypeContratSocieteDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }


    // CRUD CREER
    @Override
    public void ajouterTypeContratSociete(TypeContratSociete typeContratSociete) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO typeContratSociete(Nom, libelle, statut, pgmcreation, datecreation, usercreation ) VALUES(?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, typeContratSociete.getNom());
            preparedStatement.setString(2, typeContratSociete.getLibelle());
            preparedStatement.setString(3, typeContratSociete.getStatut());
            preparedStatement.setString(4, "TypeContratSocieteDao" );
            preparedStatement.setString(5, dateTime);
            preparedStatement.setString(6, System.getProperty("user.name"));
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException e2) {
            }
            throw new DaoException("Impossible d'ajouter enregistrement avec la table type Mission" + e);
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible d'ajouter enregistrement avec la table type Mission"+e);
            }
        }

    }

    // LISTER LES ENREGISTREMENTS
    @Override
    public List<TypeContratSociete> listerTypeContratSociete() throws DaoException {
        List<TypeContratSociete> typeContratSocietes = new ArrayList<TypeContratSociete>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT id, Nom, libelle, statut FROM typeContratSociete;");

            while (resultat.next()) {
            	int id = resultat.getInt("id");
                String Nom = resultat.getString("Nom");
                String libelle = resultat.getString("libelle");
                String statut = resultat.getString("statut");

                TypeContratSociete typeContratSociete = new TypeContratSociete();
                typeContratSociete.setId(id);
                typeContratSociete.setNom(Nom);
                typeContratSociete.setLibelle(libelle);
                typeContratSociete.setStatut(statut);

                typeContratSocietes.add(typeContratSociete);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de lister enregistrement avec la table type Mission"+e);
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de lister enregistrement avec la table type Mission"+e);
            }
        }
        return typeContratSocietes;
    }



    // CRUD LIRE UN ENREGISTREMENT SPECIFIQUE VIA SON ID
    @Override
	public TypeContratSociete trouverTypeContratSociete( Integer id ) throws DaoException {
    	TypeContratSociete typeContratSociete = new TypeContratSociete();
    	Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
        	connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT * FROM typeContratSociete WHERE ID=?;");
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            connexion.commit();

            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if ( resultat.next() ) {
                String Nom = resultat.getString("Nom");
                String libelle = resultat.getString("libelle");
                String statut = resultat.getString("statut");
                String usermodification = resultat.getString("usermodification");
                Date datemodification= (Date) resultat.getDate("datemodification");
                String pgmmodification= resultat.getString("pgmmodification");
                String usercreation = resultat.getString("usercreation");
                Date datecreation= (Date) resultat.getDate("datecreation");
                String pgmcreation= resultat.getString("pgmcreation");

                typeContratSociete.setId(id);
                typeContratSociete.setNom(Nom);
                typeContratSociete.setLibelle(libelle);
                typeContratSociete.setStatut(statut);
                typeContratSociete.setPgmmodification(pgmmodification);
                typeContratSociete.setUsermodification(usermodification);
                typeContratSociete.setDatemodification(datemodification);
                typeContratSociete.setPgmcreation(pgmcreation);
                typeContratSociete.setUsercreation(usercreation);
                typeContratSociete.setDatecreation(datecreation);
            }
        } catch ( SQLException e ) {
        	throw new DaoException("Impossible de trouver id enregistrement avec la table type Mission"+e);
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de trouver id enregistrement avec la table type Mission"+e);
            }
        }

        return typeContratSociete;
    }

    // EXISTENCE D'UN ENREGISTREMENT SPECIFIQUE VIA SON Nom
    @Override
	public boolean trouverNomTypeContratSociete( String nom ) throws DaoException {
    	boolean existe = false;
    	Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
        	connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT * FROM typeContratSociete WHERE Nom=?;");
            preparedStatement.setString(1, nom);
            resultat = preparedStatement.executeQuery();
            connexion.commit();



            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if ( resultat.next() ) {
                existe=true;
            }
        } catch ( SQLException e ) {
        	throw new DaoException("Impossible de communiquer avec la table type Mission"+e);
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la type Mission"+e);
            }
        }

        return existe;
    }


 // CRUD MODIFIER ENREGISTREMENT
 	@Override
 	public void modifierTypeContratSociete(TypeContratSociete typeContratSociete) throws DaoException {
 		 Connection connexion = null;
 	        PreparedStatement preparedStatement = null;

 	        try {
 	            connexion = daoFactory.getConnection();
 	            preparedStatement = connexion.prepareStatement("UPDATE typeContratSociete SET Nom=?, libelle=?, statut=?,  pgmmodification=?, datemodification=?, usermodification=? where id=?;");
 	            preparedStatement.setInt(7, typeContratSociete.getId());
 	            preparedStatement.setString(1, typeContratSociete.getNom());
 	            preparedStatement.setString(2, typeContratSociete.getLibelle());
 	           preparedStatement.setString(3, typeContratSociete.getStatut());
 	            preparedStatement.setString(4, "TypeContratSocieteDao" );
 	            preparedStatement.setString(5, dateTime);
 	            preparedStatement.setString(6, System.getProperty("user.name"));
 	            preparedStatement.executeUpdate();
 	            connexion.commit();
 	        } catch (SQLException e) {
 	            try {
 	                if (connexion != null) {
 	                    connexion.rollback();
 	                }
 	            } catch (SQLException e2) {
 	            }
 	            throw new DaoException("Impossible de maj enregistrement avec la table type Mission"+e);
 	        }
 	        finally {
 	            try {
 	                if (connexion != null) {
 	                    connexion.close();
 	                }
 	            } catch (SQLException e) {
 	                throw new DaoException("Impossible de maj enregistrement avec la table type Mission"+e);
 	            }
 	        }
 	}


 // CRUD COPIER ENREGISTREMENT
  	@Override
  	public void copierTypeContratSociete(TypeContratSociete typeContratSociete) throws DaoException {
  		 Connection connexion = null;
  	        PreparedStatement preparedStatement = null;

  	        try {
  	            connexion = daoFactory.getConnection();
  	          preparedStatement = connexion.prepareStatement("INSERT INTO typeContratSociete(Nom, libelle, statut, pgmcreation, datecreation, usercreation ) VALUES(?, ?, ?, ?, ?, ?);");
              preparedStatement.setString(1, typeContratSociete.getNom());
              preparedStatement.setString(2, typeContratSociete.getLibelle());
              preparedStatement.setString(3, typeContratSociete.getStatut());
              preparedStatement.setString(4, "TypeContratSocieteDao" );
              preparedStatement.setString(5, dateTime);
              preparedStatement.setString(6, System.getProperty("user.name"));
  	            preparedStatement.executeUpdate();
  	            connexion.commit();
  	        } catch (SQLException e) {
  	            try {
  	                if (connexion != null) {
  	                    connexion.rollback();
  	                }
  	            } catch (SQLException e2) {
  	            }
  	            throw new DaoException("Impossible de maj enregistrement avec la table type Mission"+e);
  	        }
  	        finally {
  	            try {
  	                if (connexion != null) {
  	                    connexion.close();
  	                }
  	            } catch (SQLException e) {
  	                throw new DaoException("Impossible de maj enregistrement avec la table type Mission"+e);
  	            }
  	        }
  	}

	// CRUD SUPPRIMER UN ENREGISTREMENT
	@Override
	public void supprimerTypeContratSociete(Integer id) throws DaoException {
    	Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
        	connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("DELETE FROM typeContratSociete WHERE ID=?;");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connexion.commit();

        } catch ( SQLException e ) {
        	throw new DaoException("Impossible de supprimer enregistrement avec la table type Mission"+e);
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de supprimer enregistrement avec la table type Mission"+e);
            }
        }

	}

	// rechercher et lister les enregistrements suivant like
	 public List<TypeContratSociete> rechercheTypeContratSocietes(int offset, int noOfRecords, String select_tri)
	    { 	Connection connexion = null;
        	PreparedStatement preparedStatement = null;
        	ResultSet rs = null;
	        String query = "select SQL_CALC_FOUND_ROWS * from typeContratSociete ORDER BY " + select_tri +" limit " + offset + ", " + noOfRecords;

	        List<TypeContratSociete> list = new ArrayList<TypeContratSociete>();
	        TypeContratSociete TypeContratSociete = null;
	        try {
	        	connexion = daoFactory.getConnection();
	        	preparedStatement = connexion.prepareStatement(query);
	            rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                TypeContratSociete = new TypeContratSociete();
	                TypeContratSociete.setId(rs.getInt(1));
	                TypeContratSociete.setNom(rs.getString(2));
	                TypeContratSociete.setLibelle(rs.getString(3));
	                TypeContratSociete.setStatut(rs.getString(4));
	                list.add(TypeContratSociete);
	            }

	            rs.close();
	            rs = preparedStatement.executeQuery("SELECT FOUND_ROWS()");

	            if (rs.next())
	               this.noOfRecords = rs.getInt(1);
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
	        finally
	        {
	            try {
	                if (connexion != null)
	                    connexion.close();
	            }
	            catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return list;
	    }

	//Avoir le N° enregistrement en cours
	 public int getNoOfRecords() { return noOfRecords; }

	// rechercher et lister les enregistrements suivant like
	 public List<TypeContratSociete> rechercheLikeTypeContratSocietes(int offset, int noOfRecords, String select_tri, String select_like)
	    { 	Connection connexion = null;
     	PreparedStatement preparedStatement = null;
     	ResultSet rs = null;
	        String query = "select SQL_CALC_FOUND_ROWS * from typeContratSociete where " + select_like + " ORDER BY " + select_tri +" limit " + offset + ", " + noOfRecords;
	        List<TypeContratSociete> list = new ArrayList<TypeContratSociete>();
	        TypeContratSociete TypeContratSociete = null;
	        try {
	        	connexion = daoFactory.getConnection();
	        	preparedStatement = connexion.prepareStatement(query);
	            rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                TypeContratSociete = new TypeContratSociete();
	                TypeContratSociete.setId(rs.getInt(1));
	                TypeContratSociete.setNom(rs.getString(2));
	                TypeContratSociete.setLibelle(rs.getString(3));
	                TypeContratSociete.setStatut(rs.getString(4));
	                list.add(TypeContratSociete);
	            }

	            rs.close();
	            rs = preparedStatement.executeQuery("SELECT FOUND_ROWS()");

	            if (rs.next())
	               this.noOfRecords = rs.getInt(1);
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
	        finally
	        {
	            try {
	                if (connexion != null)
	                    connexion.close();
	            }
	            catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return list;
	    }
}

