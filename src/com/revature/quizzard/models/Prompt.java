package com.revature.quizzard.models;

public class Prompt {
    private String response;

    public Prompt(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

//    public static Map<String, AppUser> getUsers() {
//        Map map = new HashMap<String, AppUser>();
//        try {
//            File myObj = new File(UserFilePath);
//            Scanner myReader = new Scanner(myObj);
//            while (myReader.hasNextLine()) {
//                String data = myReader.nextLine();
//                String[] userData = data.split(";", 6);
//                AppUser user = new AppUser(userData[0], userData[1], userData[2], userData[3],
//                        userData[4], Integer.parseInt(userData[5]));
//                map.put(user.getUsername(), user);
//            }
//            myReader.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//        return map;
//    }
