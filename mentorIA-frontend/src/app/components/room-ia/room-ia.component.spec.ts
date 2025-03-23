import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomIAComponent } from './room-ia.component';

describe('RoomIAComponent', () => {
  let component: RoomIAComponent;
  let fixture: ComponentFixture<RoomIAComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RoomIAComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RoomIAComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
