using System;
using System.Windows;

namespace Timer
{
    internal class AlarmBtn
    {
        String endPoint;
        bool Active;

        public AlarmBtn(String settup)
        {
            endPoint = settup;
            Active = true;
        }

        public bool CheckAlarmEnd()
        {
            String[] separator = { ":" };
            String[] userData = endPoint.Split(separator, StringSplitOptions.RemoveEmptyEntries);
            if (String.Format("{0:HH:mm}", DateTime.Now) == endPoint)
            {
                this.ToggleAlarm();
                return true;
            }
            return false;
        }
        public String GetTime()
        {
            return endPoint;
        }

        public void ToggleAlarm()
        {
            Active = !Active;
        }
        public bool IsActive()
        {
            return Active;
        }
    }
}