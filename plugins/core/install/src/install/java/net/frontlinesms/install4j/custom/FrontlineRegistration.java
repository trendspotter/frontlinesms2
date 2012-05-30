package net.frontlinesms.install4j.custom;

import java.util.HashMap;
import java.util.Map;

import com.install4j.api.context.InstallerContext;
import com.install4j.api.formcomponents.FormEnvironment;

public class FrontlineRegistration {
	public String[] prof = { "Agriculture", "Conservation", "Commercial",
			"Education", "Elections", "Emergency response", "Gender",
			"Governance", "Health", "Human rights",
			"Humanitarian Assistance", "Legal services", "Media",
			"Mobile finance", "Radio", "Other" };

	public String[] countries = { "Afghanistan", "Albania", "Algeria", "Andorra",
			"Angola", "Antigua & Deps", "Argentina", "Armenia",
			"Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain",
			"Bangladesh", "Barbados", "Belarus", "Belgium", "Belize",
			"Benin", "Bhutan", "Bolivia", "Bosnia Herzegovina", "Botswana",
			"Brazil", "Brunei", "Bulgaria", "Burkina", "Burundi",
			"Cambodia", "Cameroon", "Canada", "Cape Verde",
			"Central African Rep", "Chad", "Chile", "China", "Colombia",
			"Comoros", "Congo", "Congo {Democratic Rep}", "Costa Rica",
			"Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark",
			"Djibouti", "Dominica", "Dominican Republic", "East Timor",
			"Ecuador", "Egypt", "El Salvador", "Equatorial Guinea",
			"Eritrea", "Estonia", "Ethiopia", "Fiji", "Finland", "France",
			"Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece",
			"Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana",
			"Haiti", "Honduras", "Hungary", "Iceland", "India",
			"Indonesia", "Iran", "Iraq", "Ireland {Republic}", "Israel",
			"Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan",
			"Kazakhstan", "Kenya", "Kiribati", "Korea North",
			"Korea South", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos",
			"Latvia", "Lebanon", "Lesotho", "Liberia", "Libya",
			"Liechtenstein", "Lithuania", "Luxembourg", "Macedonia",
			"Madagascar", "Malawi", "Malaysia", "Maldives", "Mali",
			"Malta", "Marshall Islands", "Mauritania", "Mauritius",
			"Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia",
			"Montenegro", "Morocco", "Mozambique", "Myanmar, {Burma}",
			"Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand",
			"Nicaragua", "Niger", "Nigeria", "Norway", "Oman", "Pakistan",
			"Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru",
			"Philippines", "Poland", "Portugal", "Qatar", "Romania",
			"Russian Federation", "Rwanda", "St Kitts & Nevis", "St Lucia",
			"Saint Vincent & the Grenadines", "Samoa", "San Marino",
			"Sao Tome & Principe", "Saudi Arabia", "Senegal", "Serbia",
			"Seychelles", "Sierra Leone", "Singapore", "Slovakia",
			"Slovenia", "Solomon Islands", "Somalia", "South Africa",
			"South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname",
			"Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan",
			"Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga",
			"Trinidad & Tobago", "Tunisia", "Turkey", "Turkmenistan",
			"Tuvalu", "Uganda", "Ukraine", "United Arab Emirates",
			"United Kingdom", "United States", "Uruguay", "Uzbekistan",
			"Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen",
			"Zambia", "Zimbabwe" };

	public void send(FormEnvironment formEnvironment, InstallerContext context) {
		// TODO refactor this like the string stuff below!
		Integer var_category_of_work = (Integer) context
				.getVariable("var_category_of_work");
		String var_city_region = (String) context
				.getVariable("var_city_region");
		Integer var_country = (Integer) context.getVariable("var_country");
		boolean var_feature_in_user_map = context
				.getBooleanVariable("var_feature_in_user_map");
		boolean var_findout_more_about_research = context
				.getBooleanVariable("var_findout_more_about_research");
		boolean var_get_frontlinesms_newsletter = context
				.getBooleanVariable("var_get_frontlinesms_newsletter");
		String var_organization_name = (String) context
				.getVariable("var_organization_name");
		String var_share_email = (String) context
				.getVariable("var_share_email");
		String var_share_telephone_skype = (String) context
				.getVariable("var_share_telephone_skype");
		boolean var_share_your_data = context
				.getBooleanVariable("var_share_your_data");

		Map data = new HashMap();
		copyStrings(context, data,
				"var_name",
				"var_use",
				"var_website_address");
		data.put("var_operating_system", System.getProperty("os.name"));

		data.put("var_category_of_work", "" + prof[var_category_of_work]);
		data.put("var_city_region", var_city_region);
		data.put("var_country", "" + countries[var_country]);
		data.put("var_feature_in_user_map", "" + var_feature_in_user_map);
		data.put("var_findout_more_about_research", ""
				+ var_findout_more_about_research);
		data.put("var_get_frontlinesms_newsletter", ""
				+ var_get_frontlinesms_newsletter);
		data.put("var_organization_name", var_organization_name);
		data.put("var_share_email", var_share_email);
		data.put("var_share_telephone_skype", var_share_telephone_skype);
		data.put("var_share_your_data", "" + var_share_your_data);

		Testsend ts = new Testsend();
		boolean succeeded = ts.submitData(data);

		setVisible(formEnvironment, 199, succeeded);
		setVisible(formEnvironment, 200, succeeded);
		setVisible(formEnvironment, 201, !succeeded);
		setVisible(formEnvironment, 202, !succeeded);
	}

	private void copyStrings(InstallerContext from, Map to, String... keys) {
		for(String key: keys) {
			copyString(from, to, key);
		}
	}

	private void copyString(InstallerContext from, Map to, String key) {
		String value = (String) from.getVariable(key);
		to.put(key, value);
	}

	private void setVisible(FormEnvironment formEnvironment, int id, boolean visible) {
		formEnvironment.getFormComponentById(Integer.toString(id)).setVisible(visible);
	}
}
