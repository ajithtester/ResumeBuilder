package com.resumebuilder.enginerunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "Features" }, glue = { "com\\resumebuilder\\stepdefinition" },  dryRun= false)

public class EngineRunner {
	
}

