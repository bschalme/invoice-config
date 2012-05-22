<%@ page contentType="text/plain"%>
     Hello ${invoice.job.invoiceRecipient.firstName[0]},

     Here is an invoice and timesheet for <g:formatNumber number="${invoice.invoiceLine.quantity[0]}" format="##0.00" /> hours of professional services delivered during the period ${invoice.other}.

     Many thanks for your business!

     With best regards,

-- 
		${company.invoiceFirstName} ${company.invoiceLastName}
		${company.name}
		${company.url}
		Tel: ${company.phone}

