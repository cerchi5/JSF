import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "About")
@SessionScoped
public class About implements Serializable {

    private String description;
    private String contactAddress;
    private String contactPhone;
    private String contactEmail;
    private String termsOfUse;
    private String privacyPolicy;

    public About(){
        setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus dapibus ut erat vel imperdiet. Etiam at dignissim lorem, ut ultricies purus. Curabitur a enim augue. Morbi mattis commodo libero id venenatis. Cras ut congue ante. Phasellus ultrices odio ac quam aliquam, vel ullamcorper tellus eleifend. Vivamus varius vel nulla dignissim pharetra. Donec quis varius neque. In lorem elit, accumsan sit amet leo vel, euismod vestibulum odio. Donec congue at risus sed dictum. Integer non elit quis risus convallis accumsan et eget lacus. Cras porttitor pretium risus in finibus.\n" +
                "\n" +
                "Etiam vitae velit vel nulla ornare mattis. Phasellus sed varius massa. Mauris nec ante id sapien suscipit fringilla eu id augue. Phasellus eget nunc leo. Aliquam porta lorem sem, eget egestas justo imperdiet non. Fusce eu libero elit. Sed in rhoncus elit. Duis nec commodo est. Sed id leo non metus condimentum consectetur vitae auctor sapien. Quisque laoreet velit quis convallis efficitur. Nulla vulputate euismod dui eget gravida. Duis malesuada auctor augue, luctus tincidunt nulla tincidunt eget. Sed tempus lorem quis egestas eleifend.\n" +
                "\n" +
                "Proin turpis felis, condimentum in consequat vulputate, fringilla non erat. Ut volutpat malesuada enim. Sed ac accumsan orci, nec rhoncus ipsum. Proin tristique congue ligula, sit amet luctus felis aliquam vitae. Nulla condimentum, tortor ut tempus pretium, nibh dolor pharetra ex, vitae vehicula justo quam non est. Nulla quis dui mauris. Suspendisse ut dolor risus. Morbi at erat leo. Fusce placerat orci vitae eros venenatis, sit amet pharetra leo cursus. Vivamus scelerisque ipsum velit, eu placerat nisl dignissim ac. Curabitur at molestie diam, et dapibus mauris. Cras scelerisque pellentesque molestie. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;\n" +
                "\n" +
                "Ut sit amet sollicitudin massa. Sed vitae ornare augue. Curabitur laoreet turpis et efficitur suscipit. Duis pharetra sed ex id venenatis. Etiam cursus lobortis sapien, sit amet luctus mauris cursus bibendum. Nam consequat dui tellus, quis porta nisi cursus in. Nulla at lorem a orci condimentum rutrum a vel ex. Ut ut elementum mauris. Phasellus ullamcorper justo non dolor bibendum, vel varius quam posuere. Curabitur mattis tincidunt fringilla. Fusce ultrices vel mi et euismod. In sed neque vitae felis blandit tincidunt et et lectus. Maecenas commodo erat dolor, porta efficitur elit accumsan at.\n" +
                "\n" +
                "Etiam auctor auctor ligula et dapibus. Aliquam maximus pellentesque tincidunt. Praesent blandit elementum eros, semper porta mauris pulvinar sed. Mauris consequat semper metus vel tincidunt. In efficitur elementum enim in auctor. Vivamus molestie ligula vel aliquet rhoncus. Maecenas rhoncus enim id tempor gravida. Phasellus luctus tellus dolor, et laoreet leo sollicitudin in.");
        setTermsOfUse("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus dapibus ut erat vel imperdiet. Etiam at dignissim lorem, ut ultricies purus. Curabitur a enim augue. Morbi mattis commodo libero id venenatis. Cras ut congue ante. Phasellus ultrices odio ac quam aliquam, vel ullamcorper tellus eleifend. Vivamus varius vel nulla dignissim pharetra. Donec quis varius neque. In lorem elit, accumsan sit amet leo vel, euismod vestibulum odio. Donec congue at risus sed dictum. Integer non elit quis risus convallis accumsan et eget lacus. Cras porttitor pretium risus in finibus.\n" +
                "\n<br>" +
                "Etiam vitae velit vel nulla ornare mattis. Phasellus sed varius massa. Mauris nec ante id sapien suscipit fringilla eu id augue. Phasellus eget nunc leo. Aliquam porta lorem sem, eget egestas justo imperdiet non. Fusce eu libero elit. Sed in rhoncus elit. Duis nec commodo est. Sed id leo non metus condimentum consectetur vitae auctor sapien. Quisque laoreet velit quis convallis efficitur. Nulla vulputate euismod dui eget gravida. Duis malesuada auctor augue, luctus tincidunt nulla tincidunt eget. Sed tempus lorem quis egestas eleifend.\n" +
                "\n<br>" +
                "Proin turpis felis, condimentum in consequat vulputate, fringilla non erat. Ut volutpat malesuada enim. Sed ac accumsan orci, nec rhoncus ipsum. Proin tristique congue ligula, sit amet luctus felis aliquam vitae. Nulla condimentum, tortor ut tempus pretium, nibh dolor pharetra ex, vitae vehicula justo quam non est. Nulla quis dui mauris. Suspendisse ut dolor risus. Morbi at erat leo.");

        setPrivacyPolicy(getDescription());
        setContactAddress("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus dapibus ut erat vel imperdiet. Etiam at dignissim lorem, ut ultricies purus. ");
        setContactPhone("000-0123-333 444");
        setContactEmail("contact@example.com");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getTermsOfUse() {
        return termsOfUse;
    }

    public void setTermsOfUse(String termsOfUse) {
        this.termsOfUse = termsOfUse;
    }

    public String getPrivacyPolicy() {
        return privacyPolicy;
    }

    public void setPrivacyPolicy(String privacyPolicy) {
        this.privacyPolicy = privacyPolicy;
    }
}

