// Main.java
package L4_Stacks_Queues_Sets.Examples;

import L4_Stacks_Queues_Sets.*;
import exceptions.DuplicateElementException;

/*
 * Program that demonstrates set coverage.
 *
 * Suppose that an expedition to Mars was being planned.  For this expedition
 * to succeed the required set of skills must be possessed by the
 * astronauts.  Each skill must be possessed by at least on astronaut.
 * Each astronaut possess a subset of all possible skills.  To save on cost
 * for the expedition we would like to minimize the number of astronauts
 * required for the mission; this will reduce the amount of required fuel
 * and food and allow for a smaller spaceship to be constructed.
 *
 * Here is a summary of the problem:
 *       We have set1 called "skills" that contains all skills required
 *    for the mission to succeed.
 *       We have set2 called "astronauts" that contains all astronauts
 *    that could possibly be sent on the mission.  Each astronaut has a set
 *    of skills.
 *       We must select the minimum number of astronauts so that all skills
 *    are covered.
 *
 * We will accomplish this by implementing a set coverage algorithm that
 * approximates the ideal selection of astronauts.  This solution approximates
 * the solution because a best-solution would have prohibitive time complexity
 * for large numbers of skills and astronauts.
 *
 * Program Output:
        Skills that must be covered for a successful mission:
            cheerful
            cook
            engineer
            fearless
            leader
            mechanic
            navigator
            physicist
            pilot
            strong

        Astronauts to select from:
            Anna Fisher
                cheerful
                navigator
            Boris Yegerov
                cook
                mechanic
            David Wolf
                engineer
                strong
                cheerful
            Gene Cernan
                leader
            Joseph Acaba
                engineer
                physicist
            Lisa Sterling
                pilot
                navigator
                cook
                leader
            Quan Chen
                pilot
                mechanic
                cook
                fearless
            Stephen Frick
                navigator
                cook
            Stephanie Wilson
                cheerful
                cook
                strong

        -- Planning mission --
        Astronauts selected for mission:
            Lisa Sterling
            David Wolf
            Quan Chen
            Joseph Acaba
 */
public class MissionToMars {
    public static void main(String[] args) {

        // Skills that must be covered
        System.out.println(
                "Skills that must be covered for a successful mission:");
        Set<Skill> skills = loadSkills();
        for (Skill skill : skills) {
            System.out.println("\t" + skill.getName());
        }
        System.out.println();

        // Astronauts from which to select
        System.out.println("Astronauts to select from:");
        Set<Astronaut> astronauts = loadAstronauts();
        for (Astronaut astronaut : astronauts) {
            System.out.println("\t" + astronaut.getName());
            for (Skill skill : astronaut.getSkills()) {
                System.out.println("\t\t" + skill.getName());
            }
        }
        System.out.println();

        // Determine the best set of astronauts to cover the skills
        System.out.println("-- Planning mission --");
        try {
            Set<Astronaut> missionAstronauts =
                    planMission(skills, astronauts);
            System.out.println("Astronauts selected for mission:");
            for (Astronaut astronaut : missionAstronauts) {
                System.out.println("\t" + astronaut.getName());
            }
        } catch (CannotCoverException ex) {
            System.out.println(
                    "Astronauts could not cover all required skills");
        }
    }

    private static Set<Skill> loadSkills() {
        Set<Skill> skills = new Set<Skill>();

        try {
            skills.insert(new Skill("cheerful"));
            skills.insert(new Skill("cook"));
            skills.insert(new Skill("engineer"));
            skills.insert(new Skill("fearless"));
            skills.insert(new Skill("leader"));
            skills.insert(new Skill("mechanic"));
            skills.insert(new Skill("navigator"));
            skills.insert(new Skill("physicist"));
            skills.insert(new Skill("pilot"));
            skills.insert(new Skill("strong"));
        } catch (DuplicateElementException ex) {
            assert false : "Should never happen because we're "
                    + "guaranteed to not be inserting duplicates";
        }

        return skills;
    }

    private static Set<Astronaut> loadAstronauts() {
        Set<Astronaut> astronauts = new Set<Astronaut>();

        try {
            astronauts.insert(new Astronaut("Anna Fisher",
                    new Skill("cheerful"),
                    new Skill("navigator")));
            astronauts.insert(new Astronaut("Boris Yegerov",
                    new Skill("cook"),
                    new Skill("mechanic")));
            astronauts.insert(new Astronaut("David Wolf",
                    new Skill("engineer"),
                    new Skill("strong"),
                    new Skill("cheerful")));
            astronauts.insert(new Astronaut("Gene Cernan",
                    new Skill("leader")));
            astronauts.insert(new Astronaut("Joseph Acaba",
                    new Skill("engineer"),
                    new Skill("physicist")));
            astronauts.insert(new Astronaut("Lisa Sterling",
                    new Skill("pilot"),
                    new Skill("navigator"),
                    new Skill("cook"),
                    new Skill("leader")));
            astronauts.insert(new Astronaut("Quan Chen",
                    new Skill("pilot"),
                    new Skill("mechanic"),
                    new Skill("cook"),
                    new Skill("fearless")));
            astronauts.insert(new Astronaut("Stephen Frick",
                    new Skill("navigator"),
                    new Skill("cook")));
            astronauts.insert(new Astronaut("Stephanie Wilson",
                    new Skill("cheerful"),
                    new Skill("cook"),
                    new Skill("strong")));
        } catch (DuplicateElementException ex) {
            assert false : "Should never happen because we're "
                    + "guaranteed to not be inserting duplicates";
        }

        return astronauts;
    }

    private static Set<Astronaut> planMission(
            Set<Skill> skills,
            Set<Astronaut> astronauts)
            throws CannotCoverException {
        Set<Astronaut> coveringAstronauts = new Set<Astronaut>();

        // As long as skills remain to be covered and astronauts are available
        while (!skills.isEmpty() && !astronauts.isEmpty()) {
            // Get the astronaut covering the most remaining skills
            Astronaut astronaut = findBestAstronaut(skills, astronauts);

            // If no astronaut was found, total cover is not possible
            if (astronaut == null) {
                throw new CannotCoverException();
            }

            // Add astronaut to set of covering astronauts
            try {
                coveringAstronauts.insert(astronaut);
            } catch (DuplicateElementException ex) {
                assert false : "Should never happen because we're "
                        + "guaranteed to not be inserting duplicates";
            }

            // Remove astronaut from set of remaining available astronauts
            astronauts.remove(astronaut);

            // Remove skills covered by the astronaut from the skills
            // that remain to be covered
            for (Skill skill : astronaut.getSkills().intersection(skills)) {
                skills.remove(skill);
            }
        }

        // If skills remain to be covered we got here because we ran out of
        // astronauts
        if (!skills.isEmpty()) {
            throw new CannotCoverException();
        }

        return coveringAstronauts;
    }

    private static Astronaut findBestAstronaut(
            Set<Skill> skills,
            Set<Astronaut> astronauts) {
        Astronaut bestAstronaut = null;

        // Loop over all astronauts selecting one that covers the most skills
        int bestCovered = 0;
        for (Astronaut astronaut : astronauts) {
            Set<Skill> skillsCovered =
                    skills.intersection(astronaut.getSkills());
            if (skillsCovered.getSize() > bestCovered) {
                bestCovered = skillsCovered.getSize();
                bestAstronaut = astronaut;
            }
        }

        // Will be null if no astronaut covered any of the remaining skills
        return bestAstronaut;
    }
}
