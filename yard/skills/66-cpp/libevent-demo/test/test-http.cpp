/*
 * =====================================================================================
 *
 *       Filename:  test-http.cpp
 *
 *    Description:  test http operator libs
 *
 *        Version:  1.0
 *        Created:  03/12/16 13:20:47
 *       Revision:  none
 *       Compiler:  gcc/g++
 *
 *         Author:  bbxyard (Brian), bbxyard@gmail.com
 *      Copyright:  copyright (c) 2016, LGPL, bbxyard, http://www.bbxyard.com
 *
 * =====================================================================================
 */
#include "gtest/gtest.h"
#include <time.h>


class QuickTest : public testing::Test
{
protected:
    // Remember that SetUp() is run immediately before a test starts.
    // This is a good place to record the start time.
    virtual void SetUp()
    {
        start_time_ = time(NULL);
    }

    // TearDown() is invoked immediately after a test finishes.  Here we
    // check if the test was too slow.
    virtual void TearDown()
    {
        // Gets the time when the test finishes
        const time_t end_time = time(NULL);

        // Asserts that the test took no more than ~5 seconds.  Did you
        // know that you can use assertions in SetUp() and TearDown() as
        // well?
        EXPECT_TRUE(end_time - start_time_ <= 5) << "The test took too long.";
    }

    // The UTC time (in seconds) when the test starts
    time_t start_time_;
};


TEST(case1, name1)
{
    EXPECT_EQ(2, 2);
}
