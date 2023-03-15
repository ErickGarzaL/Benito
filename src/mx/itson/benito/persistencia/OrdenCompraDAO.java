/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.benito.persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.benito.entidades.Articulo;
import mx.itson.benito.entidades.OrdenCompra;
import mx.itson.benito.entidades.Proveedor;
import mx.itson.benito.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author egarz
 */
public class OrdenCompraDAO {
      public static List<OrdenCompra> obtenerTodos(){
        List<OrdenCompra> ordeCompras= new ArrayList<>();
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<OrdenCompra> criteriaQuery  = session.getCriteriaBuilder().createQuery(OrdenCompra.class);
            criteriaQuery.from(OrdenCompra.class);
            
            ordeCompras = session.createQuery(criteriaQuery).getResultList();

        }catch(Exception ex){
            System.err.println("Ocurrio un error" + ex.getMessage());
        }
             return ordeCompras;   
    }
    
       public static boolean guardar (String observacion, String folio, Date fecha, Proveedor proveedor ,  Articulo articulo ){
   
        boolean resultado = false;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            OrdenCompra o = new OrdenCompra();
            o.setObservacion(observacion);
            o.setFolio(folio);
            o.setFecha(fecha);
            o.setProveedor(proveedor);
            o.setArticulo(articulo);
      
         
            
            session.save(o);        
            session.getTransaction().commit();
            
            resultado = o.getId() !=0;
            
        }catch (Exception ex){
            System.err.println("Ocurrio un error" + ex.getMessage());
            
        }
        return resultado;
        
    }
       
           public static OrdenCompra obtenerPorId(int id ){
        OrdenCompra ordenCompra = null;
        try {
             Session session = HibernateUtil.getSessionFactory().openSession();
             ordenCompra = session.get(OrdenCompra.class, id);
             
            
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un erro " + ex.getMessage());
        
            
        }
        return ordenCompra;
    }
        
           
           
     public  static boolean editar ( int id, String observacion, String folio, Date fecha, Proveedor proveedor ,  Articulo articulo  ){
        boolean resultado  = false;
    try {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    OrdenCompra ordenCompra = obtenerPorId(id);
    if(ordenCompra != null){      
       ordenCompra.setId(id);
       ordenCompra.setObservacion(observacion);
       ordenCompra.setFolio(folio);
       ordenCompra.setFecha(fecha);
       ordenCompra.setProveedor(proveedor);
       ordenCompra.setArticulo(articulo);
        
        
        session.saveOrUpdate(ordenCompra);
        session.getTransaction().commit();
        resultado = true;
    }
    
         
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error " + ex.getMessage());
        
           
        }
      return resultado;
}
     
     
     public boolean eliminar (int id ){
    boolean resultado  = false;
    
     try {
          Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    OrdenCompra ordenCompra = obtenerPorId(id);
    if(ordenCompra != null){
        session.delete(ordenCompra);
        session.getTransaction().commit();
        resultado = true;
    }
         
         
         } catch (HibernateException ex) {
            System.err.println("Ocurrio un error " + ex.getMessage());
        
           
        }
      return resultado;
     }      
           
           
    
    
}


