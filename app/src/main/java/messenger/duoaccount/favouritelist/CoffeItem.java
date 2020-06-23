package messenger.duoaccount.favouritelist;

public class CoffeItem {

    private int imageResources;
    private String title;
    private String key_id;
    private String favStatus;

    public CoffeItem() {
    }

    public CoffeItem(int imageResources, String title, String key_id, String favStatus) {
        this.imageResources = imageResources;
        this.title = title;
        this.key_id = key_id;
        this.favStatus = favStatus;
    }

    public int getImageResources() {
        return imageResources;
    }

    public void setImageResources(int imageResources) {
        this.imageResources = imageResources;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(String favStatus) {
        this.favStatus = favStatus;
    }
}
