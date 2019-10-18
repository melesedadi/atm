package com.example.demo;
/*
Write an Spring Boot application simulating a ATM at a bank.

The application should allow people to make a deposit or a withdrawal with a form and show their account balance.

So, you will need the following pages:

Registration form
Login form (for collecting user's account number)
Deposit form
Withdrawal form
For your model, you will need at least (and possibly more)

Transaction

id # (autoincrement)
account #
action (withdrawal or deposit)
amount
Account Balance
Users should be able to create an account and use their account number to validate their credentials and make transactions (deposit or withdraw money).

Users must validate account information (password) before making each transaction.

You must have at least three users and each user must have at least two accounts and each account must contain multiple transactions.

Use Bootstrap and make it look professional.

 Done Already?

Add a transaction history reflecting each deposit/withdrawal

Publish to Heroku
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    DepositRepository depositRepository;
    @Autowired
    WithdrawRepository withdrawRepository;
    @Autowired
    RegistrationRepository registrationRepository;

    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/")
    public String homepage(Model model) {
        model.addAttribute("deposits", depositRepository.findAll());
        model.addAttribute("withdraws", withdrawRepository.findAll());
        model.addAttribute("registrations", registrationRepository.findAll());
        return "index";
    }

    @RequestMapping("/registrationlist")
    public String departmentList(Model model) {
        model.addAttribute("registrations", registrationRepository.findAll());
        return "registrationlist";

    }

    @GetMapping("/addregistration")
    public String registrationForm(Model model) {
        model.addAttribute("registration", new Registration());
        return "registrationform";
    }

    @PostMapping("/processregistration")
    public String processRegistrationForm(@Valid Registration registration,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "registrationform";
        }
        registrationRepository.save(registration);
        return "redirect:/registrationlist";
    }
    @RequestMapping("/withdrawlist")
    public String employeeList(Model model){
        model.addAttribute("withdraws", withdrawRepository.findAll());
        return "withdrawlist";
    }

    @GetMapping("/addwithdraw")
    public String employeeForm(Model model){
        model.addAttribute("withdraw", new Withdraw());
        model.addAttribute("deposits", depositRepository.findAll());
        model.addAttribute("registrations", registrationRepository.findAll());
        return "withdrawform";
    }
    @PostMapping("/processwithdraw")
    public String processWithdrawForm(@Valid Withdraw withdraw,
                               BindingResult result){
        if (result.hasErrors()){
            return "withdrawform";
        }
        withdrawRepository.save(withdraw);
        return "redirect:/withdrawlist";
    }


    @RequestMapping("/depositlist")
    public String depositList(Model model){
        model.addAttribute("deposits", depositRepository.findAll());
        return "depositlist";
    }

    @GetMapping("/adddeposit")
    public String depositForm(Model model){
        model.addAttribute("deposit", new Deposit());
        model.addAttribute("withdraws", withdrawRepository.findAll());
        model.addAttribute("registrations", registrationRepository.findAll());
        return "depositform";
    }
    @PostMapping("/procesdeposit")
    public String processDepositForm(@Valid Deposit deposit,
                                      BindingResult result){
        if (result.hasErrors()){
            return "depositform";
        }
        depositRepository.save(deposit);
        return "redirect:/depositlist";
    }

    @RequestMapping("/detailregistration/{id}")
    public String showRegistration(@PathVariable("acountID") long accountID, Model model)
    {model.addAttribute("registration", registrationRepository.findAll());
        return "showregistration";
    }
    @RequestMapping("/updateregistration/{id}")
    public String updateRegistration(@PathVariable("accountID") long accountID,Model model){
        model.addAttribute("registration",registrationRepository.findById(accountID).get());
        return "registrationform";
    }
    @RequestMapping("/deleteregistration/{id}")
    public String delRegistration(@PathVariable("acountID") long accountID){
        depositRepository.deleteById(accountID);
        return "redirect:/";
    }
    @RequestMapping("/detailwithdraw/{id}")
    public String showWithdraw(@PathVariable("id") long id, Model model)
    {model.addAttribute("withdraw", withdrawRepository.findById(id).get());
        return "showwithdraw";
    }
    @RequestMapping("/updatewithdraw/{id}")
    public String updateWithdraw(@PathVariable("id") long id,Model model){
        model.addAttribute("registration", depositRepository.findById(id).get());
        model.addAttribute("withdraws", withdrawRepository.findAll());
         return "withdrawform";
    }
    @RequestMapping("/deletewithdraw/{id}")
    public String delWithdraw(@PathVariable("id") long id){
        withdrawRepository.deleteById(id);
        return "redirect:/";
    }
    @RequestMapping("/detaildeposit/{id}")
    public String showDeposit(@PathVariable("id") long id, Model model)
    {model.addAttribute("deposit", depositRepository.findById(id).get());
        return "showdeposit";
    }
    @RequestMapping("/updatedeposit/{id}")
    public String updateDeposit(@PathVariable("id") long id,Model model){
        model.addAttribute("registration", registrationRepository.findById(id).get());
        model.addAttribute("deposits", depositRepository.findAll());
        return "depositform";
    }
    @RequestMapping("/deletedeposit/{id}")
    public String deldeposit(@PathVariable("id") long id){
        withdrawRepository.deleteById(id);
        return "redirect:/";
    }
}

