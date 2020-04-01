package iti.alex.intake40.team9.tripreminder.Room;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

@Entity(tableName = "TripModel")
public class TripModel {
         public TripModel(){}
//        @PrimaryKey(autoGenerate = true)
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "trip_id")
        private int id;
        @ColumnInfo(name = "trip_title")
        private String title;
        @ColumnInfo(name = "trip_startPoint")
        private String startPoint;
        @ColumnInfo(name = "trip_endPoint")
        private String endPoint;
        @ColumnInfo(name = "trip_dateTime")
        private Long dateTime;
        @Nullable
        @ColumnInfo(name = "trip_repetition")
        private String repetition;
        @Nullable
        @ColumnInfo(name = "trip_rounded")
        private String rounded;

        @Nullable
        @ColumnInfo(name = "trip_notes")
        @TypeConverters({Converter.class})
        private List<String> notes;


        @Nullable
        @ColumnInfo(name = "trip_importance")
        private String importance;

        @ColumnInfo(name = "trip_history")
        private Boolean history;
        @NonNull
        public int getId() {
                return id;
        }

        public void setId(@NonNull int id) {
                this.id = id;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getStartPoint() {
                return startPoint;
        }

        public void setStartPoint(String startPoint) {
                this.startPoint = startPoint;
        }

        public String getEndPoint() {
                return endPoint;
        }

        public void setEndPoint(String endPoint) {
                this.endPoint = endPoint;
        }

        public Long getDateTime() {
                return dateTime;
        }

        public void setDateTime(Long dateTime) {
                this.dateTime = dateTime;
        }

        public String getRepetition() {
                return repetition;
        }

        public void setRepetition(String repetition) {
                this.repetition = repetition;
        }

        public String getRounded() {
                return rounded;
        }

        public void setRounded(String rounded) {
                this.rounded = rounded;
        }
        @Nullable
        public List<String> getNotes() {
                return notes;
        }

        public void setNotes(  @Nullable List<String> notes) {
                this.notes = notes;
        }
        @Nullable
        public String getImportance() {
                return importance;
        }

        public void setImportance(  @Nullable String importance) {
                this.importance = importance;
        }

        public Boolean getHistory() {
                return history;
        }

        public void setHistory(Boolean history) {
                this.history = history;
        }



        public TripModel(@NonNull int id, String title, String startPoint, String endPoint, Long dateTime, String repetition, String rounded,   @Nullable List<String> notes,   @Nullable String importance, Boolean history) {
                this.id = id;
                this.title = title;
                this.startPoint = startPoint;
                this.endPoint = endPoint;
                this.dateTime = dateTime;
                this.repetition = repetition;
                this.rounded = rounded;
                this.notes = notes;
                this.importance = importance;
                this.history = history;
        }
}