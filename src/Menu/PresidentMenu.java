// * @author Malthe
// * 18/05/2021 15.07
// *
// * DAT21V2-Projekt-Delfinen
// *

package Menu;

import DelfinMain.DelfinMain;
import Member.Member;
import Member.MemberHandler;
import Member.MemberList;
import Service.FileControl;
import Service.UserInput;
import org.beryx.textio.TextIO;

import java.util.Arrays;

public class PresidentMenu {
    private static Boolean isRunning = true;
    private static final String menuText = "Welcome to President menu!";
    private static final String[] menuOptions = {
            "Add new member.",
            "Remove member.",
            "Update member information.",
            "See list of members",
            "Go back to main menu",
    };

    public static void runPresidentMenu() {
        isRunning = true;
        UserInput.clearConsole();

        while (isRunning) {
            Integer menuChoice = UserInput.askForMenuChoice(menuText, Arrays.asList(menuOptions));

            switch (menuChoice) {
                case 0 -> AddMember();
                case 1 -> RemoveMember();
                case 2 -> UpdateMember();
                case 3 -> showMemberList();
                case 4 -> ExitMenu();
            }
        }
    }

    private static void AddMember() {
       MemberHandler.addMember();
       FileControl.writeSerializableToFile(DelfinMain.listOfMembers,"list");
    }

    private static void RemoveMember() {
        MemberHandler.removeMember();
        FileControl.writeSerializableToFile(DelfinMain.listOfMembers,"list");
    }

    private static void UpdateMember() {
        MemberHandler.updateMemberInformation();
        FileControl.writeSerializableToFile(DelfinMain.listOfMembers,"list");
    }

    public static void showMemberList(){
        var listOfIds = DelfinMain.listOfMembers.getFieldFromAllMembers(Member -> Member.getName() + ": with member id: " + Member.getMemberId() + ". Is member active: " + Member.isActiveMember() + "\n");
        if (listOfIds.isEmpty()){
            UserInput.console.println("There is currently no numbers");
        }
        else
        UserInput.console.println(String.valueOf(listOfIds));
    }

    private static void ExitMenu() {
        isRunning = false;
        UserInput.clearConsole();
    }
}

