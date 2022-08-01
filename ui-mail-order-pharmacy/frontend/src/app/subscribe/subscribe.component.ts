import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PrescriptionDetails } from '../prescription-details';
import { SubscribeService } from '../subscribe.service';
import { AuthService } from '../auth.service';
@Component({
  selector: 'app-subscribe',
  templateUrl: './subscribe.component.html',
  styleUrls: ['./subscribe.component.css']
})
export class SubscribeComponent implements OnInit {
  msg !: string;
  todayDate: Date=new Date();
  memberId: string =localStorage.getItem("userId") || " ";
  prescriptionDetails : PrescriptionDetails={ 'prescriptionId' :0 ,
  'memberId' : this.memberId ,
  'memberLocation' : '',
  'policyNumber' : '',
  'insuranceProvider' : '',
  'prescriptionDate' : this.todayDate,
  'drugName' : '',
  'dosageDefinition' : 'morning',
  'quantity' : 0,
  'courseDuration' : 0,
  'doctorName' : '',
};
  constructor(private route: ActivatedRoute, private service: SubscribeService, private router:Router,private authService:AuthService) {

    if(!this.authService.isLoggedIn()){
      this.router.navigate(["/"]);
    }
   }

  ngOnInit(): void {
  }
  public cities:string[]=['Bangalore','Chennai','Hyderabad','Pune']
  public meds:string[]=['Paracetmol','Citrazine','Aspirin']
  public insurances:string[]=['Aditya Birla','Bajaj Allianz','Bharti AXA','IFFCO Tokio']
handleSubmit()
{
  console.log(typeof this.prescriptionDetails);
  this.service.savePrescription(this.prescriptionDetails).subscribe(data=>
    {
     // this.msg=data
     console.log('in data');
     this.msg = "You have successfully subscribed to "+this.prescriptionDetails.drugName;
      console.log(data);
      this.router.navigate(['subscriptions'])

    },error => {
      console.log(error);
      this.msg = "Sorry , Please try again late"
    })
}

}
