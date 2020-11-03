package main;
//http://jfuzzylogic.sourceforge.net/html/manual.html#plugin

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;
import net.sourceforge.jFuzzyLogic.ruleConnectionMethod.RuleConnectionMethodAndMin;
import net.sourceforge.jFuzzyLogic.ruleConnectionMethod.RuleConnectionMethodOrMax;
import net.sourceforge.jFuzzyLogic.rule.Rule;
import net.sourceforge.jFuzzyLogic.rule.RuleBlock;
import net.sourceforge.jFuzzyLogic.rule.RuleExpression;
import net.sourceforge.jFuzzyLogic.rule.RuleTerm;

public class TestRotatingSolar {

	public static void main(String[] args) throws Exception {
		// Load from 'FCL' file
		String fileName = "/home/isuru/Downloads/jfuzzylogic/src/main/rotating_solar.FCL";
		FIS fis = FIS.load(fileName, true);
		if (fis == null) { // Error while loading?
			System.err.println("Can't load file: '" + fileName + "'");
			return;
		}

		// Show ruleset
		FunctionBlock functionBlock = fis.getFunctionBlock(null); //FunctionBlock's name (can be null to retrieve first available one)
		JFuzzyChart.get().chart(functionBlock);

		// Set inputs
		functionBlock.setVariable("north_south_difference", 0);
		functionBlock.setVariable("east_west_difference", 0);

		// Evaluate 
		functionBlock.evaluate();

		// Show output variable's chart
		Variable motor_we = functionBlock.getVariable("motor_we");
		JFuzzyChart.get().chart(motor_we, motor_we.getDefuzzifier(), true);

		Variable motor_ns = functionBlock.getVariable("motor_ns");
		JFuzzyChart.get().chart(motor_ns, motor_ns.getDefuzzifier(), true);

		// Print ruleSet
		System.out.println("motor_we:" + functionBlock.getVariable("motor_we").getValue());
		System.out.println("motor_ns:" + functionBlock.getVariable("motor_ns").getValue());

		System.out.println("No1 rule set\n");
		RuleBlock ruleBlock = fis.getFunctionBlock("solar_tracker").getFuzzyRuleBlock("No1");
		// Show each rule (and degree of support)
		for( Rule r : ruleBlock.getRules() ) 
			System.out.println(r);
		
		System.out.println("\nNo2 rule set\n");
		ruleBlock = fis.getFunctionBlock("solar_tracker").getFuzzyRuleBlock("No2");
		// Show each rule (and degree of support)
		for( Rule r : ruleBlock.getRules() )
			System.out.println(r);
		
	    

	}
}