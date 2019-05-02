package detector;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import smell.Smell;
import smell.Smell.Abusers;
import smell.Smell.Bloaters;
import smell.Smell.Couplers;
import smell.Smell.Dispensables;
import smell.abuser.Abuser;
import smell.bloater.Bloater;
import smell.coupler.Coupler;
import smell.dispensable.Dispensable;

/**
 * A class that reports on the different types of smells to users
 */
public class Report {

    private Abuser abuser;
    private Bloater bloater;
    private Coupler coupler;
    private Dispensable dispensable;
    private Statistics smellStatistics;

    public Report(Detector detector) {
        this.abuser = detector.abuser;
        this.bloater = detector.bloater;
        this.coupler = detector.coupler;
        this.dispensable = detector.dispensable;
    }

    /**
     * Analyzes the Project for abusers, bloaters, couplers and dispensables
     *
     * @param cu - the project in compilation unit
     */
    public void analyzeProject() {
        smellStatistics = new Statistics(this.abuser, this.bloater, this.coupler, this.dispensable);
    }

    /**
     * Show all the computed smell statistics
     *
     * @return statistics about smells in formatted string
     */
    public String showSmellStatistics() {
        return smellStatistics.toString();
    }


    /**
     * Returns the statistics object to do more calculations
     */

    public Statistics fetchStats() {
        return smellStatistics;
    }

    ;

    /**
     * Used to generate text files based on the report for abuser, bloater, couplers and
     * dispensables
     * @throws FileNotFoundException 
     */
    public void generateTextFileReport(String fileNameOut) throws FileNotFoundException {
        //===================Type of Smell Categories
        List<Map<String, Map<Abusers, Smell>>> abuses = this.abuser.getAbusers();
        List<Map<String, Map<Bloaters, Smell>>> bloats = this.bloater.getBloaters();
        List<Map<String, Map<Couplers, Smell>>> couples = this.coupler.getCouplers();
        List<Map<String, Map<Dispensables, Smell>>> dispenses = this.dispensable.getDispensables();

        PrintWriter writer = new PrintWriter(fileNameOut);
        //CREATE A FILE FOR ABUSER, BLOATER, COUPLER AND DISPENSABLE
        //===================ABUSERS
        for (Map<String, Map<Abusers, Smell>> map : abuses) {
            for (String fileName : map.keySet()) {    //FileName
                for (Abusers abuse : map.get(fileName).keySet()) {    //Enum
                    if (!map.get(fileName).get(
                            abuse).getIssue().isEmpty()) { //Dont print things that are empty
                        writer.print(fileName + "\r\n" + map.get(fileName).get(abuse).toString(
                                abuse.toString())+"\r\n");
                    }
                }
            }
        }

        //===================BLOATERS
        for (Map<String, Map<Bloaters, Smell>> map : bloats) {
            for (String fileName : map.keySet()) {    //FileName
                for (Bloaters bloat : map.get(fileName).keySet()) {    //Enum
                    if (!map.get(fileName).get(
                            bloat).getIssue().isEmpty()) { //Dont print things that are empty
                    	writer.print(fileName + "\r\n" + map.get(fileName).get(bloat).toString(
                                bloat.toString())+"\r\n");
                    }
                }
            }
        }

        //===================COUPLERS
        for (Map<String, Map<Couplers, Smell>> map : couples) {
            for (String fileName : map.keySet()) {    //FileName
                for (Couplers couple : map.get(fileName).keySet()) {    //Enum
                    if (!map.get(fileName).get(
                            couple).getIssue().isEmpty()) { //Dont print things that are empty
                    	writer.print(fileName + "\r\n" + map.get(fileName).get(couple).toString(
                                couple.toString())+"\r\n");
                    }
                }
            }
        }

        //===================DISPENSABLES
        for (Map<String, Map<Dispensables, Smell>> map : dispenses) {
            for (String fileName : map.keySet()) {    //FileName
                for (Dispensables dispense : map.get(fileName).keySet()) {    //Enum
                    if (!map.get(fileName).get(
                            dispense).getIssue().isEmpty()) { //Dont print things that are empty
                    	writer.print(fileName + "\r\n" + map.get(fileName).get(
                                dispense).toString(dispense.toString())+"\r\n");
                    }
                }
            }
        }
        
        writer.close();
    }


}
