package lesson11.grouping;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Groups {
    @Test(groups = {"first"})
    public void one() {
        Assert.assertTrue(true);
    }

    @Test(groups = {"second"})
    public void two() {
        Assert.assertTrue(true);
    }

    @Test(groups = {"first"}, dependsOnMethods = {"one"})
    public void three() {
        Assert.assertTrue(true);
    }

    @Test(groups = {"second"}, dependsOnMethods = {"two"})
    public void four() {
        Assert.assertTrue(true);
    }

    @Test(groups = {"first"}, dependsOnMethods = {"three"})
    public void five() {
        Assert.assertTrue(true);
    }

    @Test(groups = {"second"}, dependsOnMethods = {"four"})
    public void six() {
        Assert.assertTrue(true);
    }

    @Test(groups = {"first"}, dependsOnMethods = {"five"})
    public void seven() {
        Assert.assertTrue(true);
    }

    @Test(groups = {"second"}, dependsOnMethods = {"six"})
    public void eight() {
        Assert.assertTrue(true);
    }
}
