package org.compiere.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Listado de listas de StorageVO
 * @author Omar de la Rosa
 *
 */
public class StorageVOList extends ArrayList<List<StorageVO>> {

	private static final long serialVersionUID = 1L;

	@Override	
	public boolean add(List<StorageVO> o) {
		int M_Product_ID = 0;
		if(o.size() > 0){
			M_Product_ID = o.get(0).getM_Product_ID();
			int indice = indexOf(M_Product_ID);
			if(indice != -1){
				set(indice, o);
//				add(indice, o);
//				try{
//					remove(indice+1);
//				}catch(IndexOutOfBoundsException e){
//					//No habia otros elementos en la lista
//				}
			}else{
				super.add(o);
			}
		}
		return true;
	}
	
	/**
	 * Obtiene lista de StorageVO
	 * @param M_Product_ID Id del producto
	 * @return Lista de StorageVO o null si no la encuentra
	 */
	public List<StorageVO> getByProductID(int M_Product_ID){
		List<StorageVO> o = null;
		int index = indexOf(M_Product_ID);
		if(index != -1)
			o = get(index);
		return o;
	}
	
	/**
	 * Obtiene el indice de la lista
	 * @param M_Product_ID Id del producto
	 * @return Indice de la lista o -1 si no la encuentra
	 */
	public int indexOf(int M_Product_ID){
		for(int i = 0; i < size(); i++){
			StorageVO tmp = get(i).get(0);
			if(tmp.getM_Product_ID() == M_Product_ID){
				return i;
			}
		}
		return -1;
	}
	
}
