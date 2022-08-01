import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PrescriptionDetails } from './prescription-details';
import { SubscriptionDetails } from './subscription-details';

@Injectable({
  providedIn: 'root',
})
export class SubscribeService {
  constructor(private http: HttpClient) {}

  baseUrl: string = 'http://localhost:8048/subscription-service/subscriptionapi';  //http://localhost:8048/subscription-service/subscriptionapi

  getAllSubscriptions(mId: string) {
    let token = localStorage.getItem('token');
    console.log(mId, token);
    const header = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };
    return this.http.get<SubscriptionDetails[]>(
      `${this.baseUrl}/getAllSubscriptions/${mId}`,
      header
    );
  }

  unsubscribe(mId: string, sId: number) {
    let token = localStorage.getItem('token');
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        Authorization: `Bearer ${token}`,
      }),
      responseType: 'text',
    };

    console.log(mId, sId);
    return this.http.post<any>(
      `${this.baseUrl}/unsubscribe/${mId}/${sId}`,
      null,
      HTTPOptions
    );
  }

  savePrescription(prescriptionDetails: PrescriptionDetails) {
    let token = localStorage.getItem('token');
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        Authorization: `Bearer ${token}`,
      }),
      responseType: 'text',
    };

    console.log(prescriptionDetails);
    return this.http.post<any>(
      `${this.baseUrl}/subscribe`,
      prescriptionDetails,
      HTTPOptions
    );
  }
}
