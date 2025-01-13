package com.jsfcourse.actor;

import com.jsfcourse.actor.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.Flash;

import com.jsf.dao.ActorDAO;
import com.jsf.entities.Actor;


@Named
@RequestScoped
public class ActorListBB {
      private static final String PAGE_ACTOR_EDIT = "actorEdit?faces-redirect=true";;
      private static final String PAGE_STAY_AT_THE_SAME = null;
    @Inject
    ExternalContext extcontext;

    @Inject
    Flash flash;

    @EJB
    ActorDAO actorDAO;

    // Retrieve the full list of actors
    public List<Actor> getFullList() {
        List<Actor> list = null;
        list = actorDAO.getFullList();
        return list;
    }
    
    
        public String deleteActor(Actor actor){
        actorDAO.remove(actor);
        return PAGE_STAY_AT_THE_SAME;
    }
        public String editActor(Actor actor){
        flash.put("actor", actor);      
        return PAGE_ACTOR_EDIT;
    }

}
