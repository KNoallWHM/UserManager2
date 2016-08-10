package springBootApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springBootApp.entities.User;
import springBootApp.entities.UserDao;

/**
 * Created by Superduo on 8/8/16.
 */
@Controller
public class JspController {
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/")
    public String jspIndex() {
        return "index";
    }

    @RequestMapping(value = "/jspTest")
    public String jspTest() {
        return "test";
    }

//    @RequestMapping(value = "/jspcreate")
//    public String jspcreateUser() {
//        return "createUser";
//    }

@RequestMapping(value = "/jspcreate")
public String jspcreateUser(String email, String name) {
        try {
            User user = new User(email, name);
            userDao.save(user);
            return "User "+user.getId()+" created!";
        }
        catch (Exception e) {
            return "Error creating user: " + e.toString();
        }
    }


//    @RequestMapping(value = "/jspReadUser")
//    public String Iterable<User1> getUser() {}
//        {return "readuser";}
    // this method will create this object into a json
    @RequestMapping(value = "/jspReadUser")
    // an iterable is a type of collection. (the same as an array list)
    public Iterable<User> getUser() {
        return userDao.findAll();
    }
    @RequestMapping(value = "/getUser")
    public User getUser(long id) {
        return userDao.findOne(id);
    }

    @RequestMapping(value = "/getUserByEmail")
    public User getByEmail(String email) {
        return userDao.findByEmail(email);
    }


//    @RequestMapping(value = "/jspUpdateUser")
//    public String jspUpdateUser() {return "updateuser";}

    @RequestMapping(value = "/updateUser")
    public String jspUpdateUser(long id, String email, String name) {
        try {
            User user = userDao.findOne(id);
            user.setEmail(email);
            user.setName(name);
            userDao.save(user);
            return "User "+id+" updated!";
        }
        catch (Exception e) {
            return "Error updating user: " + e.toString();
        }
    }

//    @RequestMapping(value = "/jspDeleteUser")
//    public String jspDeleteUser() {return "deleteuser";}


    @RequestMapping(value = "/jspDeleteUser")
    public String jspDeleteUser(long id) {
        try {
            userDao.delete(userDao.findOne(id));
            return "User "+id+" deleted!";
        }
        catch (Exception e) {
            return "Error deleting user:" + e.toString();
        }
    }


}