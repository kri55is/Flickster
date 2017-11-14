package com.codepath.flickster.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

    public class Movie {

        @SerializedName("vote_count")
        @Expose
        private Integer voteCount;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("video")
        @Expose
        private Boolean video;
        @SerializedName("vote_average")
        @Expose
        private Double voteAverage;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("popularity")
        @Expose
        private Double popularity;
        @SerializedName("poster_path")
        @Expose
        private String posterPath;
        @SerializedName("original_language")
        @Expose
        private String originalLanguage;
        @SerializedName("original_title")
        @Expose
        private String originalTitle;
        @SerializedName("genre_ids")
        @Expose
        private List<Integer> genreIds = null;
        @SerializedName("backdrop_path")
        @Expose
        private String backdropPath;
        @SerializedName("adult")
        @Expose
        private Boolean adult;
        @SerializedName("overview")
        @Expose
        private String overview;
        @SerializedName("release_date")
        @Expose
        private String releaseDate;

        public Integer getVoteCount() {
            return voteCount;
        }

        public void setVoteCount(Integer voteCount) {
            this.voteCount = voteCount;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Boolean getVideo() {
            return video;
        }

        public void setVideo(Boolean video) {
            this.video = video;
        }

        public Double getVoteAverage() {
            return voteAverage;
        }

        public Double getVoteAverage(int div) {
//          we get the rating which is a rating on 10. Here we might want to get the rating on something else.
            if(div >0) {
                double result = (voteAverage / 10) * div;
                return result;
            }
            else
                return 0.0;
        }

        public void setVoteAverage(Double voteAverage) {
            this.voteAverage = voteAverage;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Double getPopularity() {
            return popularity;
        }

        public void setPopularity(Double popularity) {
            this.popularity = popularity;
        }

        public String getPosterPath() {
            return String.format("https://image.tmdb.org/t/p/w342%s", posterPath);
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public String getOriginalLanguage() {
            return originalLanguage;
        }

        public void setOriginalLanguage(String originalLanguage) {
            this.originalLanguage = originalLanguage;
        }

        public String getOriginalTitle() {
            return originalTitle;
        }

        public void setOriginalTitle(String originalTitle) {
            this.originalTitle = originalTitle;
        }

        public List<Integer> getGenreIds() {
            return genreIds;
        }

        public void setGenreIds(List<Integer> genreIds) {
            this.genreIds = genreIds;
        }

        public String getBackdropPath() {
            return String.format("https://image.tmdb.org/t/p/w342%s", backdropPath);
        }

        public void setBackdropPath(String backdropPath) {
            this.backdropPath = backdropPath;
        }

        public Boolean getAdult() {
            return adult;
        }

        public void setAdult(Boolean adult) {
            this.adult = adult;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

    }

//    String posterPath;
//    String originalTitle;
//    String overview;
//    String backdropImage;
//    float rating;
//    String videoPath;
//
//    public String getPosterPath() {
//        return String.format("https://image.tmdb.org/t/p/w342%s", posterPath);
//    }
//
//    public String getOriginalTitle() {
//        return originalTitle;
//    }
//
//    public String getOverview() {
//        return overview;
//    }
//
//    public String getBackdropImage() {
//        return String.format("https://image.tmdb.org/t/p/w342%s", backdropImage);
//
//    }
//    public float getRating(int div) {
//        //we get the rating which is a rating on 10. Here we might want to get the rating on something else.
//        if(div >0) {
//            float result = (rating / 10) * div;
//            return result;
//        }
//        else
//            return 0;
//    }
//
//
//    public Movie (JSONObject jsonObject) throws JSONException{
//        this.posterPath = jsonObject.getString("poster_path");
//        this.originalTitle = jsonObject.getString("original_title");
//        this.overview = jsonObject.getString("overview");
//        this.backdropImage = jsonObject.getString("backdrop_path");
//        this.rating =(float) jsonObject.getDouble("vote_average");
//
//    }
//
//    public static ArrayList<Movie> fromJSONArray(JSONArray array){
//        ArrayList<Movie> results = new ArrayList<>();
//
//        for (int x = 0 ; x < array.length(); x++){
//
//            try {
//                results.add(new Movie(array.getJSONObject(x)));
//            }
//            catch (JSONException e){
//                e.printStackTrace();
//            }
//        }
//        return results;
//    }
//}
