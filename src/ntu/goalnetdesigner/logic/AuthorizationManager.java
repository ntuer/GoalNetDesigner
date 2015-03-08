package ntu.goalnetdesigner.logic;

import java.util.ArrayList;
import java.util.List;

import ntu.goalnetdesigner.data.persistence.Gnet;
import ntu.goalnetdesigner.data.persistence.User;
import ntu.goalnetdesigner.data.persistence.UserGnet;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.session.LoginSession;
import ntu.goalnetdesigner.utility.Resource;

public class AuthorizationManager {
	
	public boolean login(String id, String password){
		User user = DataService.user.find(id);
		if (user == null) 
			return false;
		if (!user.getPassword().equals(password))
			return false;
		LoginSession.user = user;
		LoginSession.isLoggedIn = true;
		return true;
	}

	public boolean register(User u) {
		try{
			DataService.user.atomicInsert(u);
			return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean changePassword(User user, String text) {
		try{
			DataService.begin();
			user.setPassword(text);
			DataService.commit();
			return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public String getGnetAccessLevelOfUser(User user, Gnet gnet){
		List<UserGnet> ugList = user.getUserGnets();
		for (UserGnet ug: ugList){
			if (ug.getGnet().getId().equals(gnet.getId()))
				return ug.getAccessLevel();
		}
		return "";
	}
	
	public ArrayList<Gnet> getReadableGnetsOfUser(User user){
		ArrayList<Gnet> gnets = new ArrayList<>();
		for (UserGnet ug: user.getUserGnets()){
			gnets.add(ug.getGnet());
		}
		return gnets;
	}
	
	public ArrayList<Gnet> getWriteableGnetsOfUser(User user){
		ArrayList<Gnet> gnets = new ArrayList<>();
		for (UserGnet ug: user.getUserGnets()){
			if (ug.getAccessLevel().equals(Resource.UserGnetAccessLevel.WRITE) || 
					ug.getAccessLevel().equals(Resource.UserGnetAccessLevel.ADMIN))
				gnets.add(ug.getGnet());
		}
		return gnets;
	}
	
	public ArrayList<String> getUsersOfGnetByAccessLevel(Gnet gnet, String accessLevel){
		ArrayList<String> users = new ArrayList<>();
		for (UserGnet ug: gnet.getUserGnets()){
			if (ug.getAccessLevel().equals(accessLevel))
				users.add(ug.getUser().getId());
		}
		return users;
	}
}
