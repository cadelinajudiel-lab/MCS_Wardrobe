package Practical.Exam;

import androidx.annotation.NonNull;

import org.junit.Test;

/*
 * Member Class Test
 */
public class Dimasangal_MemberClassTest {

    @Test
    public void testMemberClass() {

        Member member =
                new Member("Denise", "Premium");

        System.out.println(
                "Member Name: "
                        + member.name()
        );

        System.out.println(
                "Membership Type: "
                        + member.membershipType()
        );

        System.out.println(member);
    }
}

/*
 * Member class stores gym member information.
 */
record Member(String name, String membershipType) {

    @NonNull
    @Override
    public String toString() {

        return name +
                " (" +
                membershipType +
                ")";
    }
}