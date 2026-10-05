package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CourseDetailPage extends CommonPage{

    // Locator
    private By byTitlePage;
    private By byNameCourse;
    private By byInstructor;
    private By byCourseCategory;
    private By byRatingScore;
    private By byNumberOfReviews;
    private By byCourseDescription;
    private By byCourseContent;
    private By byCourseImage;
    private By byCoursePrice;
    private By byNumberOfStudents;
    private By byStudyDuration;
    private By byNumberOfLessons;
    private By byNumberOfVideos;
    private By byCourseLevel;
    private By byBtnRegister;
    private By byLesson;
    private By byRelatedCourses;

    public CourseDetailPage(WebDriver driver) {
        super(driver);
        this.byTitlePage = By.xpath("//div[@class='titleCourse']/h3");
       this.byNameCourse  = By.xpath("");
       this.byInstructor = By.xpath("");
       this.byCourseCategory = By.xpath("");
       this.byRatingScore = By.xpath("");
       this.byNumberOfReviews = By.xpath("");
       this.byCourseDescription = By.xpath("");
       this.byCourseContent = By.xpath("");
       this.byCourseImage = By.xpath("");
       this.byCoursePrice = By.xpath("");
       this.byNumberOfStudents = By.xpath("");
       this.byStudyDuration = By.xpath("");
       this.byNumberOfLessons = By.xpath("");
       this.byNumberOfVideos = By.xpath("");
       this.byCourseLevel = By.xpath("");
       this.byBtnRegister = By.xpath("//button[text()='Đăng ký']");
       this.byLesson = By.xpath("");
       this.byRelatedCourses = By.xpath("");
    }

    public String getTitleDetailPage()
    {
        return getText(byTitlePage);
    }
    public void waitRegisterButtonClickable()
    {
        waitElementToBeClickable(byBtnRegister);
    }

    public void clickRegisterButton()
    {
        waitElementToBeClickable(byBtnRegister);
        click(byBtnRegister);
    }
}
