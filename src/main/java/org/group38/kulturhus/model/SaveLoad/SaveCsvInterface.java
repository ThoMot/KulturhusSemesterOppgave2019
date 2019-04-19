package org.group38.kulturhus.model.SaveLoad;

import org.group38.kulturhus.model.Event.EventNumberedSeating;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SaveCsvInterface implements SaveDataInterface {

        private boolean append;

        public void SubstituteWriterCSV(boolean append) {
            this.append = append;
        }

        @Override
        public boolean writeEvent(EventNumberedSeating numberedSeating) {
            FileWriter fileWriter = null;
            List<EventNumberedSeating> substituteUser = new ArrayList<>();
            final String DELIMETER = ",";
            final String NEWLINE = "\n";

            substituteUser.add(numberedSeating);


            try {
                fileWriter = new FileWriter("users.csv", append);
                for (EventNumberedSeating substituteUserInfo : substituteUser) {
                    fileWriter.append(String.valueOf(substituteUserInfo.getEventInfo().getEventName()));
                    fileWriter.append(DELIMETER);
                    fileWriter.append(String.valueOf(substituteUserInfo.getEventInfo().getProgram()));
                    fileWriter.append(DELIMETER);
                    fileWriter.append(String.valueOf(substituteUserInfo.getEventInfo().getDate()));
                    fileWriter.append(DELIMETER);
                    fileWriter.append(String.valueOf(substituteUserInfo.getEventInfo().getTime()));
                    fileWriter.append(DELIMETER);
                    fileWriter.append(String.valueOf(substituteUserInfo.getFacility().getFacilityName()));
                    fileWriter.append(DELIMETER);
                    fileWriter.append(String.valueOf(substituteUserInfo.getEventInfo().getDate()));
                    fileWriter.append(DELIMETER);
                    fileWriter.append(String.valueOf(substituteUserInfo.getFacility()));
                    fileWriter.append(NEWLINE);

                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                try {
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void setAppend(boolean append) {
            this.append = append;
        }
    }


