package ntu.goalnetdesigner.data.service;

import ntu.goalnetdesigner.data.persistence.*;
import ntu.goalnetdesigner.data.service.*;

public class DataService {
	public static DataServiceUnit<Arc> arc = new DataServiceUnit<Arc>(Arc.class);
	public static DataServiceUnit<Function> function = new DataServiceUnit<Function>(Function.class);
	public static DataServiceUnit<Gnet> gnet = new DataServiceUnit<Gnet>(Gnet.class);
	public static DataServiceUnit<Property> property = new DataServiceUnit<Property>(Property.class);
	public static DataServiceUnit<State> state = new DataServiceUnit<State>(State.class);
	public static DataServiceUnit<Task> task = new DataServiceUnit<Task>(Task.class);
	public static DataServiceUnit<Tasklist> tasklist = new DataServiceUnit<Tasklist>(Tasklist.class);
	public static DataServiceUnit<User> user = new DataServiceUnit<User>(User.class);
	public static DataServiceUnit<Usergroup> usergroup = new DataServiceUnit<Usergroup>(Usergroup.class);
}
