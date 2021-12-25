package simple.example.apibahasapemrograman;

public class DataBahasa {
    String name, desc,logo,helloWord,readMore;

    public DataBahasa(String name, String desc, String logo, String helloWord, String readMore) {
        this.name = name;
        this.desc = desc;
        this.logo = logo;
        this.helloWord = helloWord;
        this.readMore = readMore;
    }

    public String getName(){
        return name;
    }
    public String getDesc(){
        return desc;
    }
    public String getLogo(){
        return logo;
    }
    public String getHelloWord(){
        return helloWord;
    }
    public String getReadMore(){
        return readMore;
    }
}
