package com.ecaresoft.api.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para agregar funcionalidad de Tags simple en los objetos que extiendan
 * @author ecaresoft-pedro
 *
 */

public abstract class Taggable {
	public static final String TAG_TRASH = "trash";
	public static final String TAG_SELECTED = "selected";
	public static final String TAG_NEW = "new";
	public static final String TAG_SAVED = "saved";
	public static final String TAG_TEMPORAL = "temporal";
	public static final String TAG_EDITED = "edited";
	private List<String> tags;

	public final void addTag(String Tag){
		if(tags == null){
			tags = new ArrayList<String>();
			tags.add(Tag);
		}else if(tags.indexOf(Tag) == -1){
			tags.add(Tag);
		}
		
	}
	
	public final void addTags(String... tags){
		for(String tag : tags){
			addTag(tag);
		}
	}
	
	public final void removeTags(String... tags){
		for(String tag : tags){
			removeTag(tag);
		}
	}
	
	public final void removeTag(String Tag){
		if(tags != null){
			int index = findTagIndex(Tag);
			if(index >= 0){
				tags.remove(index);
			}
		}
	}
	
	private final int findTagIndex(String... Tag){
		for(int i = 0; i < tags.size(); i++){
			for(String tag : Tag){
				if(tags.get(i).equals(tag)){
					return i;
				}
			}			
		}
		return -1;
	}
	public final boolean hasTag(String... Tag){
		if(tags != null){
			return findTagIndex(Tag) > -1;
		}else{
			return false;
		}
	}
	
	public final void clearTags(){
		tags = null;
	}
	
	public final List<String> getTags(){
		return tags;
	}
	
	/**
	 * @param tagableList
	 * @param has
	 * @param tags
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Taggable> List<T> filter(List<? extends T> tagableList,boolean has, String... tags) {
		List<T> list = new ArrayList<T>();
		if(tagableList != null){	
			for(Taggable tagable : tagableList){
				if(tagable.hasTag(tags) == has){
					list.add((T)tagable);
				}
			}
		}
		return list;
	}
	
	public interface TagFilter{
		public boolean validate(Taggable tagableObject );
	}
	@SuppressWarnings("unchecked")
	public static <T extends Taggable> List<T> filter(List<? extends T> tagableList,TagFilter tagFilter) {
		
		List<T> list = new ArrayList<T>();
		if(tagFilter != null && tagableList != null){
			for(Taggable tagable : tagableList){
				if(tagFilter.validate(tagable)){
					list.add((T)tagable);
				}
			}
		}
		return list;
	}
	
	public static int count(List<? extends Taggable> tagableList, boolean has, String... tags) {
		int count = 0;
		if(tagableList != null){		
			for(Taggable tagable : tagableList){
				if(tagable.hasTag(tags) == has){
					count++;
				}
			}
		}
		return count;
	}
	
	public static final TagFilter TAG_FILTER_TO_SAVE = new TagFilter() {		
		@Override
		public boolean validate(Taggable tagableObject) {
			return ((tagableObject.hasTag(Taggable.TAG_NEW) || tagableObject.hasTag(Taggable.TAG_TEMPORAL) || tagableObject.hasTag(Taggable.TAG_EDITED) ) && !tagableObject.hasTag(Taggable.TAG_TRASH));			
		}
	};
	public static final TagFilter TAG_FILTER_TO_DELETE = new TagFilter() {		
		@Override
		public boolean validate(Taggable tagableObject) {
			return tagableObject.hasTag(Taggable.TAG_TRASH) && !tagableObject.hasTag(Taggable.TAG_NEW);			
		}
	};
}
