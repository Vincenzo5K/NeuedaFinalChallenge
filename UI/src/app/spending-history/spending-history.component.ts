import { ChartOptions, ChartType, Chart  } from 'chart.js';
import { Component, OnInit, ElementRef, ViewChild, AfterViewInit } from '@angular/core';
import { Observable} from 'rxjs';
import { ApiService } from '../api.service';
import { LowHigh } from '../models/LowHigh';
@Component({
  selector: 'app-spending-history',
  templateUrl: './spending-history.component.html',
  styleUrls: ['./spending-history.component.css']
})
export class SpendingHistoryComponent implements OnInit, AfterViewInit  {
  @ViewChild('pieChartCanvas') private pieChartCanvasRef: ElementRef;
  private pieChart: Chart<"pie", any[], unknown> | undefined;
  lh: string;
  inputElement:HTMLInputElement;
  amount: number[]=[10,20,30,40,50,60,70,80,90,100];
  lower: number=0;
  higher: number=0;
  lowhigh!: Observable<LowHigh[]>;
  ngAfterViewInit(): void {
    this.createInitialChart();
  }

  constructor(private transactionService: ApiService) { }

  ngOnInit(): void {
  }

  createInitialChart(): void {
    const pieChartCanvas = this.pieChartCanvasRef.nativeElement.getContext('2d');
    new Chart(pieChartCanvas, {
      type: 'pie',
      data: {
        labels: ['Male', 'Female'],
        datasets: [{
          data: [300, 450],
          backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56']
        }]
      },
      options: {
        responsive: true,
      }
    });
  }

  delay(ms: number): Promise<void> {
    return new Promise((resolve) => setTimeout(resolve, ms));
  }
    
  async example() {
    console.log('Start');
    await this.delay(7000); // Delay for 2000 milliseconds (2 seconds)
    this.generateHigherLowerChart();
    console.log('End');
  }
  

  getLowerHigher(): void{
    this.inputElement = document.getElementById("filterTextbox") as HTMLInputElement;
    this.lh=this.inputElement.value,10;
    this.lower=0;
    this.higher=0;
    // this.amount.forEach((element, index) => {
    //   if(element<=this.lh)
    //     this.lower++;
    //   else
    //     this.higher++;
    // })

    this.lowhigh = this.transactionService.getLowHighData(this.lh);
    console.warn(this.lowhigh)

    this.lowhigh.subscribe(
      (items: LowHigh[]) => {
        // Access properties of each item in the array
        for (const item of items) {
          console.log('lowHigh:', item.lowHigh);
          console.log('transTotal:', item.transTotal);
          
          if(item.lowHigh=="Low Amounts")
            this.lower=item.transTotal;
          else if(item.lowHigh=="High Amounts")
            this.higher=item.transTotal;
        }
      },
      (error) => {
        console.error('Error:', error);
        // Handle the error here
      }
    );

    //this.generateHigherLowerChart();
    this.example()

  
  }

  generateHigherLowerChart(): void{
    if (Chart.getChart("myChart")){
      Chart.getChart("myChart").destroy();
    }



    const pieChartCanvas = this.pieChartCanvasRef.nativeElement.getContext('2d');
    const data = this.getChartDataForSelection("lowerhigher");

    this.pieChart = new Chart(pieChartCanvas, {
      type: 'pie',
      data: {
        labels: data.labels,
        datasets: [{
          data: data.data,
          backgroundColor: data.backgroundColor
        }]
      },
      options: {
        responsive: true,
      }
    });
  }

  generateChart(event: Event): void {
    const target = event.target as HTMLSelectElement;
    const selectedValue = target.value;

    if (Chart.getChart("myChart")){
      Chart.getChart("myChart").destroy();
    }

    const pieChartCanvas = this.pieChartCanvasRef.nativeElement.getContext('2d');
    const data = this.getChartDataForSelection(selectedValue);

    this.pieChart = new Chart(pieChartCanvas, {
      type: 'pie',
      data: {
        labels: data.labels,
        datasets: [{
          data: data.data,
          backgroundColor: data.backgroundColor
        }]
      },
      options: {
        responsive: true,
      }
    });
  }

  getChartDataForSelection(selection: string): any {
    // Define your data based on the selection here
    // Example data structure:
   
    switch (selection) {
      case 'gender':
        return {
          labels: ['Male', 'Female'],
          data: [300, 450],
          backgroundColor: ['#FF6384', '#36A2EB']
        };
      case 'category':
        return {
          labels: ['Category 1', 'Category 2', 'Category 3'],
          data: [100, 200, 300],
          backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56']
        };
        case 'merchant':
        return {
          labels: ['Merchant 1', 'Merchant 2'],
          data: [100, 200, 300],
          backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56']
        };
        case 'city':
        return {
          labels: ['City 1', 'City 2', 'City 3', 'City 4'],
          data: [100, 200, 300, 400],
          backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#000000']
        };
        case 'state':
        return {
          labels: ['State 1', 'State 2'],
          data: [100, 200, 300],
          backgroundColor: ['#FF6384', '#36A2EB']
        };
        case 'lowerhigher':
          console.log(this.lower)
          console.log(this.higher)
          return {

            labels: ['Lower', 'Higher'],
            data: [this.lower,this.higher],
            backgroundColor: ['#FF6384', '#36A2EB']
          };
      // Add more cases for other selections
      default:
        return {
          labels: [],
          data: [],
          backgroundColor: []
        };
    }
  }
}
