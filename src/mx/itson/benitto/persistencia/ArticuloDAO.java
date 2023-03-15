/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.benitto.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.benitto.entidades.Articulo;
import mx.itson.benitto.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author egarz
 */
public class ArticuloDAO {
    
     public static List<Articulo> obtenerTodos(){
        List<Articulo> articulos = new ArrayList<>();
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Articulo> criteriaQuery  = session.getCriteriaBuilder().createQuery(Articulo.class);
            criteriaQuery.from(Articulo.class);
            
            articulos = session.createQuery(criteriaQuery).getResultList();

        }catch(Exception ex){
            System.err.println("Ocurrio un error" + ex.getMessage());
        }
             return articulos;   
    }
     
     
         public static boolean guardar (String nombre, String clave, double precio){
   
        boolean resultado = false;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Articulo a = new Articulo();
            a.setNombre(nombre);
            a.setClave(clave);
            a.setPrecio(precio);
         
         
            
            session.save(a);        
            session.getTransaction().commit();
            
            resultado = a.getId() !=0;
            
        }catch (Exception ex){
            System.err.println("Ocurrio un error" + ex.getMessage());
            
        }
        return resultado;
        
    }
         
         
         public static Articulo obtenerPorId(int id ){
        Articulo articulo = null;
        try {
             Session session = HibernateUtil.getSessionFactory().openSession();
             articulo = session.get(Articulo.class, id);
             
            
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un erro " + ex.getMessage());
        
            
        }
        return articulo;
    }
     
         
         
         
     public  static boolean editar ( int id, String clave, double precio){
        boolean resultado  = false;
    try {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Articulo articulo = obtenerPorId(id);
    if(articulo != null){      
       articulo.setNombre(clave);
       articulo.setClave(clave);
       articulo.setPrecio(precio);
        
        session.saveOrUpdate(articulo);
        session.getTransaction().commit();
        resultado = true;
    }
    
         
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un erro " + ex.getMessage());
        
           
        }
      return resultado;
}
     
   
     
     
     public boolean eliminar (int id ){
    
    boolean resultado  = false;
    
     try {
          Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Articulo articulo = obtenerPorId(id);
    if(articulo != null){
        session.delete(articulo);
        session.getTransaction().commit();
        resultado = true;
    }
         
         
         
         
         } catch (HibernateException ex) {
            System.err.println("Ocurrio un erro " + ex.getMessage());
        
           
        }
      return resultado;
     }
     
     
     
}


