/*
    Copyright 2012 Airspeed Consulting

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
package ca.airspeed.invoice

class InvoiceLine {

    static constraints = {
		quantity(min:0F, max: 10000F, scale: 2)
		itemName(blank: false, size: 3..25)
		description(blank: false, size: 5..65)
		rate(min: 0F, max: 999999.9F, scale: 2)
		amount(min: 0F, max: 9999999.9F, scale: 2)
    }
	
	Float quantity
	String itemName
	String description
	Float rate
	Float amount 
}
